package com.telusko.spring_security_project_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//@Configuration tells Spring:      "This class contains Spring configuration/beans."     Spring will detect this class and use the configuration inside it.
@Configuration

//This enables Spring Security's web security functionality.   It allows Spring Security to intercept incoming HTTP requests.
@EnableWebSecurity



public class SecurityConfig {




    //Authentication Provider

    @Autowired
    public UserDetailsService userDetailsService;
    //userDetailsService is an interface so we have to implement it



    @Autowired
    private JwtFilter jwtFilter;



    @Bean
    public AuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);

       //Use BCrypt with a cost factor of 12 when verifying passwords for this authentication provider.
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return provider;

    }




    //You're creating a Spring bean called SecurityFilterChain.   A SecurityFilterChain defines the security rules that Spring Security applies to incoming requests.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //This disables CSRF protection       CSRF means:Cross-Site Request Forger    CSRF protection is particularly important for applications using browser cookies/session-based authentication.
        http.csrf(customizer -> customizer.disable());

        //This is saying:  Every request must be authenticated.
        http.authorizeHttpRequests(request ->
                request
                        .requestMatchers("/register", "/login").permitAll()
                        .anyRequest().authenticated()
        );


        //This tells Spring Security:  Use HTTP Basic Authentication.  The client sends username and password in the request.
        http.httpBasic(Customizer.withDefaults());

        //STATELESS means  ::  Spring Security will not create/use an HTTP session to remember the user's authentication.
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


       //This takes all your security configuration and builds the actual SecurityFilterChain.
        return http.build();
    }





    // ***  Create a Bean for Authentication Manager For JWT Authentication


    @Bean  //now the authenticationManager can be autowired in the controller means we can use the AuthenticationManager object in the controller

    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();

    }








    //*** To work with the multiple user
    //create userDetailsService (HardCoded userDetailsservice)


//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user= User.withDefaultPasswordEncoder().
//                username("navin").
//                password("@123").
//                roles("USER").
//                build();
//
//
//        UserDetails admin= User.withDefaultPasswordEncoder().
//                username("admin").
//                password("admin@123").
//                roles("ADMIN").
//                build();
//
//
//        return new InMemoryUserDetailsManager(user,admin);
//
//
//    }

}