package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AuthStudentDto;
import com.gmail.mileshko.lesya.schedule.dto.GradebookDto;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth/student")
public class AuthStudentController {

    private  final StudentService studentService;

    @Autowired
    public AuthStudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public String authenticate(@RequestBody AuthStudentDto authStudentDto) throws AuthenticationException, NoSuchEntityException{
        return studentService.authenticate(authStudentDto);
    }

}
