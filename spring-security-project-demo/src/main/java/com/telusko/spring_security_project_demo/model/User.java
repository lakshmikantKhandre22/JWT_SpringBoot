package com.telusko.spring_security_project_demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.security.PrivilegedAction;

@Entity //this class is map to the table in the database
@Data  //this is lombok anotation used to create automatic getters and setters

@Table(name="users") //this class mapped with the table in the database

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;




}
