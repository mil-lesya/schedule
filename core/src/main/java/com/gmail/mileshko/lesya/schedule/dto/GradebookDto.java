package com.gmail.mileshko.lesya.schedule.dto;

public class GradebookDto {
    public Integer gradebook;
    public AssessmentDto assessment;

    public GradebookDto() {
    }

    public GradebookDto(Integer gradebook, AssessmentDto assessment) {
        this.gradebook = gradebook;
        this.assessment = assessment;
    }
}
