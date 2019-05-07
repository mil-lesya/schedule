package com.gmail.mileshko.lesya.schedule.dto;

public class GradebookDto {
    public String gradebookNumber;
    public AssessmentDto assessment;

    public GradebookDto() {
    }

    public GradebookDto(String gradebookNumber, AssessmentDto assessment) {
        this.gradebookNumber = gradebookNumber;
        this.assessment = assessment;
    }
    
}
