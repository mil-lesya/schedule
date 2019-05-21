package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AuthStudentDto;
import com.gmail.mileshko.lesya.schedule.dto.GradebookDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
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

        @GetMapping("validate")
        public StudentDto validate(@RequestHeader("token") String token) throws NoSuchEntityException {
            Student student = studentService.validate(token);
            return Mapper.map(student, StudentDto.class);
        }

}
