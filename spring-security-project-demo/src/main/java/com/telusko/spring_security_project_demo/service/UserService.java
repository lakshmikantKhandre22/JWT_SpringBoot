package com.telusko.spring_security_project_demo.service;


import com.telusko.spring_security_project_demo.dao.UserRepo;
import com.telusko.spring_security_project_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    //usersevice requires the dependency of userRepo to save the user in the database

    @Autowired
    private UserRepo userRepo;

    //we have to store the user password in the ByCrypt Format USE THE Class of spring security BCryptPasswordEncodes

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);



    public User Saveuser(User user)
     {
      //set the user password as BCrypt password
      user.setPassword(encoder.encode(user.getPassword()));
      System.out.println(user.getPassword());

      //call the repo to save the user
      return userRepo.save(user);

     }

}
