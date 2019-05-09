package com.gmail.mileshko.lesya.schedule.dto;

import java.util.List;

public class GradebookDto {
    public String gradebookNumber;
    public List<AssessmentDto> assessments;
    public StudentDto student;

    public GradebookDto() {
    }

    public GradebookDto(String gradebookNumber, List<AssessmentDto> assessments, StudentDto student) {
        this.gradebookNumber = gradebookNumber;
        this.assessments = assessments;
        this.student = student;
    }
}
