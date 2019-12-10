package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("assessment")
public class AssessmentController {
    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping("delete")
    @PreAuthorize("hasRole('LECTURER')")
    public void deleteAssessments(@RequestBody Long assessmentId) {
        assessmentService.deleteAssessment(assessmentId);
    }

    @PostMapping("add")
    @PreAuthorize("hasRole('LECTURER')")
    public void addAssessment(@Valid @RequestBody NewAssessmentDto newAssessmentDto) {
        assessmentService.addAssessment(newAssessmentDto);
    }
}
