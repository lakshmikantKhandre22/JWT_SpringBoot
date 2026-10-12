package com.telusko.spring_security_project_demo.controller;


import com.telusko.spring_security_project_demo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    List<Student> students=new ArrayList<>(List.of
            (new Student(1,"Raj","Java"),
            new Student(2,"Yash","JS")
            ));


    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return students;

    }


    @PostMapping("/students")
    public void addstudent(@RequestBody Student student)
    {
        students.add(student);

    }



}
