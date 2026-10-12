package com.telusko.spring_security_project_demo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service //it is a service layer spring creates its object ans managed it as spring bean to use it in other classes
public class JwtService {



    //secret key should be kept secret and not hardcoded in the code. It should be stored in a secure location, such as an environment variable or a configuration file that is not checked into version control. For demonstration purposes, we are using a hardcoded key here, but in a real application, you should use a secure key management solution.
    private static final String SECRET="GDFHGXhdzhfHJGHGSDFZJhhdsadvvdfjgbxctuerytrgdgdfgbysdtfysvb=\n\r";



//    private String secretKey;
//
//    public JwtService()
//    {
//        secretKey=generateSecretKey();
//
//    }



    // Method to generate a secret key

//    public String generateSecretKey() {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        return Base64.getEncoder().encodeToString(key.getEncoded());
//    }


    public String generateToken(String username) {

        Map<String,Object> claims=new HashMap<>();


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*3))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);// Use a secure key
        return Keys.hmacShaKeyFor(keyBytes);

    }







    //extract the username from the token

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //generic claim extraction
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    //Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    //validating the token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

    //checking token expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
