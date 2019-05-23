package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.RegisterStudentDto;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("register/student")
public class RegistrationStudentController {
    private  final StudentService studentService;

    @Autowired
    public RegistrationStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void register(@Valid @RequestBody RegisterStudentDto registerStudentDto) throws NoSuchEntityException,  RegistrationException {
        studentService.register(registerStudentDto);
    }
}
