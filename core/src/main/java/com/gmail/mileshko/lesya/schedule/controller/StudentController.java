package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {
    private  final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("get")
    public StudentDto get(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.map(student, StudentDto.class);
    }
}
