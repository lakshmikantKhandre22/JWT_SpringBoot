package com.telusko.spring_security_project_demo.dao;

import com.telusko.spring_security_project_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



//using spring data JPA To perfrom the database related operation \
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUsername(String username);


}
