package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AuthAdminDto;
import com.gmail.mileshko.lesya.schedule.dto.NewStudent;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.AdminService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//TODO: authorization for admin
@RestController
@RequestMapping("student")
public class StudentController {
    private  final StudentService studentService;
    private final AdminService adminService;

    @Autowired
    public StudentController(StudentService studentService, AdminService adminService) {
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @GetMapping("get")
    public StudentDto get(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.map(student, StudentDto.class);
    }

    @PostMapping("new")
    public void saveStudent(@Valid @RequestBody NewStudent newStudent, @RequestHeader("token") String token) throws NoSuchEntityException, AuthenticationException {
        adminService.validate(token);
        studentService.saveStudent(newStudent);
    }

    @GetMapping("delete")
    public void deleteStudent(@RequestParam("studentId") Long studentId, @RequestHeader("token") String token) throws NoSuchEntityException, AuthenticationException {
        adminService.validate(token);
        studentService.deleteStudent(studentId);
    }

    @PostMapping("headman")
    public void appointHeadman( @RequestBody Long headmanId) throws NoSuchEntityException {
        studentService.appointHeadman(headmanId);
    }
}
