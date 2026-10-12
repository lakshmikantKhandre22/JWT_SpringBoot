package com.telusko.spring_security_project_demo.controller;

import com.telusko.spring_security_project_demo.model.User;
import com.telusko.spring_security_project_demo.service.JwtService;
import com.telusko.spring_security_project_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {


    //User controller requires the dependency of user service

    @Autowired
   private UserService userservice;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;


    //controller to register the user in the database using the user service
    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        return  userservice.Saveuser(user);

    }






    //** Custom Login Controller with JWT Authentication


    @PostMapping("/login")
    public String login(@RequestBody User user)  //in the post request data is sent in the request body
    {
    //return type is string because we are returning the token as string


        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

         if(authentication.isAuthenticated())
         {
             return jwtService.generateToken(user.getUsername()); //generate the token for the user
         }

         else {
            return "Failure";

         }

    }
    }



