package com.telusko.spring_security_project_demo.service;

import com.telusko.spring_security_project_demo.dao.UserRepo;
import com.telusko.spring_security_project_demo.model.User;
import com.telusko.spring_security_project_demo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service //this is service layer spring creates its object ans managed it as spring bean

public class MyUserDetailsService implements UserDetailsService {


    //to load the user we need the user
    //so create a userRepo to get the user details from the database

    @Autowired
    public UserRepo repo;



    //userDetails is an interface so we have to implement it


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{


        //get the user from the database using the UserRepo interface

        User user=repo.findByUsername(username);

        if(user==null)
        {
            System.out.println("user 404");
            throw new UsernameNotFoundException("user 404");

        }

        return new UserPrincipal(user);


    }
}
