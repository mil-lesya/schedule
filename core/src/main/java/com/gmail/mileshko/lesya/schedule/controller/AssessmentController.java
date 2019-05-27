package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthorizationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.AssessmentService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("assessment")
public class AssessmentController {
    private final AssessmentService assessmentService;
    private final StudentService studentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService, StudentService studentService) {
        this.assessmentService = assessmentService;
        this.studentService = studentService;
    }

    @PostMapping("delete")
    public void deleteAssessments(@RequestBody Long assessmentId, @RequestHeader("token") String token) throws NoSuchEntityException{
        Student headman = studentService.validate(token);
        studentService.isHeadman(headman);
        assessmentService.deleteAssessment(assessmentId);
    }

    @PostMapping("add")
    public void addAssessment(@Valid @RequestBody NewAssessmentDto newAssessmentDto, @RequestHeader("token") String token) throws NoSuchEntityException, AuthorizationException {
        Student student = studentService.validate(token);
        assessmentService.addAssessment(newAssessmentDto, student);
    }
}
