package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthorizationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AssessmentRepository;
import com.gmail.mileshko.lesya.schedule.repository.StudentRepository;
import com.gmail.mileshko.lesya.schedule.service.AssessmentService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gradebook")
public class GradebookController {
    private  final StudentService studentService;
    private  final StudentRepository studentRepository;
    private final AssessmentService assessmentService;

    @Autowired
    public GradebookController(StudentService studentService, StudentRepository studentRepository, AssessmentService assessmentService) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public List<AssessmentDto> getAssessments(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.mapAll(assessmentService.getGradebookAssessments(student), AssessmentDto.class);
    }

    @GetMapping("student")
    public List<AssessmentDto> getStudentAssessments(@RequestParam("studentId") Long id) throws NoSuchEntityException {
        Student student = studentRepository.findById(id).orElseThrow(()-> new NoSuchEntityException("") );
        return Mapper.mapAll(assessmentService.getGradebookAssessments(student), AssessmentDto.class);
    }



    @PostMapping("edit")
    public void editAssessments(@RequestBody List<AssessmentDto> assessmentsDto) throws NoSuchEntityException, AuthorizationException {
        assessmentService.editAssessment(assessmentsDto);
    }

    @PostMapping("add")
    public void addAssessment(@RequestBody List<NewAssessmentDto> newAssessmentsDto) throws NoSuchEntityException {
        assessmentService.addAssessment(newAssessmentsDto);
    }

    @GetMapping("headman")
    public boolean isHeadman(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student headman = studentService.validate(token);
        return studentService.isHeadman(headman);
    }

}

