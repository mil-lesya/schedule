package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AuthStudentDto;
import com.gmail.mileshko.lesya.schedule.dto.GradebookDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.io.IOException;

@RestController
@RequestMapping("auth/student")
public class AuthStudentController {

    private  final StudentService studentService;

    @Autowired
    public AuthStudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public String authenticate(@RequestBody AuthStudentDto authStudentDto, @RequestBody GradebookDto gradebookDto) throws AuthenticationException, NoSuchEntityException{
        return studentService.authenticate(authStudentDto, gradebookDto);
    }

}
