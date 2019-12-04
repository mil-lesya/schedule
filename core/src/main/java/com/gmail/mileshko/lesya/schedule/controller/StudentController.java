package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.NewStudent;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.service.UserService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("student")
public class StudentController {
    private  final StudentService studentService;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('STUDENT')")
    public StudentDto get(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Student student = studentService.getStudent(user);
        return Mapper.map(student, StudentDto.class);
    }

    @PostMapping("new")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveStudent(@Valid @RequestBody NewStudent newStudent, @RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException, AuthenticationException {
        studentService.changeGroup(newStudent);
    }

    @GetMapping("delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@RequestParam("studentId") Long studentId) throws NoSuchEntityException, AuthenticationException {
        studentService.deleteStudent(studentId);
    }

    @PostMapping("headman")
    @PreAuthorize("hasRole('ADMIN')")
    public void appointHeadman(@RequestBody Long headmanId) throws NoSuchEntityException {
        studentService.appointHeadman(headmanId);
    }
}
