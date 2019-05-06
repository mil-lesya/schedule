package com.gmail.mileshko.lesya.schedule.dto;

public class GradebookDto {
    public String gradebook;
    public AssessmentDto assessment;

    public GradebookDto() {
    }

    public GradebookDto(String gradebook, AssessmentDto assessment) {
        this.gradebook = gradebook;
        this.assessment = assessment;
    }
    
}
