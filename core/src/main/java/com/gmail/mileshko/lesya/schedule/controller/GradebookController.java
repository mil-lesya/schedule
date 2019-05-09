package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
import com.gmail.mileshko.lesya.schedule.dto.EditAssessmentDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Assessment;
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
    private final AssessmentRepository assessmentRepository;

    @Autowired
    public GradebookController(StudentService studentService, StudentRepository studentRepository, AssessmentService assessmentService, AssessmentRepository assessmentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.assessmentService = assessmentService;
        this.assessmentRepository = assessmentRepository;
    }

    @GetMapping()
    public List<AssessmentDto> getAssessments(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.mapAll(studentService.getGradebookAssessments(student), AssessmentDto.class);
    }

    @GetMapping("student")
    public List<AssessmentDto> getStudentAssessments(@RequestParam("studentId") Long id) throws NoSuchEntityException {
        Student student = studentRepository.findById(id).orElseThrow(()-> new NoSuchEntityException("") );
        return Mapper.mapAll(studentService.getGradebookAssessments(student), AssessmentDto.class);
    }



    @PostMapping("edit")
    public AssessmentDto editAssessment( AssessmentDto assessmentDto, EditAssessmentDto editAssessmentDto) throws NoSuchEntityException, AuthorizationException {
        Assessment assessment = assessmentRepository.findById(assessmentDto.id).orElseThrow(()-> new NoSuchEntityException(""));
        return Mapper.map(assessmentService.editAssessment(assessment, editAssessmentDto), AssessmentDto.class);
    }



    @GetMapping("headman")
    public boolean isHeadman(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student headman = studentService.validate(token);
        return studentService.isHeadman(headman);
    }

}

