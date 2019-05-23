package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.NewStudent;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//TODO: authorization for admin
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

    @PostMapping("new")
    public void saveStudent(@Valid @RequestBody NewStudent newStudent) throws NoSuchEntityException {
        studentService.saveStudent(newStudent);
    }

    @PostMapping("delete")
    public void deleteStudent( @RequestBody Long studentId) throws NoSuchEntityException {
        studentService.deleteStudent(studentId);
    }

    @PostMapping("headman")
    public void appointHeadman( @RequestBody Long headmanId) throws NoSuchEntityException {
        studentService.appointHeadman(headmanId);
    }
}
