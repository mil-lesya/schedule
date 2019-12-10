package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.AssessmentService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.service.UserService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("gradebook")
public class GradebookController {
    private  final StudentService studentService;
    private final AssessmentService assessmentService;
    private final UserService userService;

    @Autowired
    public GradebookController(StudentService studentService, AssessmentService assessmentService, UserService userService) {
        this.studentService = studentService;
        this.assessmentService = assessmentService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public List<AssessmentDto> getAssessments(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Student student = studentService.getStudent(user);
        return Mapper.mapAll(assessmentService.getGradebookAssessments(student), AssessmentDto.class);
    }

    @GetMapping("student")
    @PreAuthorize("hasRole('LECTURER')")
    public List<AssessmentDto> getStudentAssessments(@RequestParam("studentId") Long id) throws NoSuchEntityException {
        Student student = studentService.getStudentById(id);
        return Mapper.mapAll(assessmentService.getGradebookAssessments(student), AssessmentDto.class);
    }

    @GetMapping("headman")
    @PreAuthorize("hasRole('STUDENT')")
    public boolean isHeadman(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Student headman = studentService.getStudent(user);
        return studentService.isHeadman(headman);
    }
}

