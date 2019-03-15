package com.gmail.mileshko.lesya.schedule.dto;

public class AssessmentDto {
    public SubjectDto subgect;
    public SessionDto session;
    public Integer mark;

    public AssessmentDto() {
    }

    public AssessmentDto(SubjectDto subgect, SessionDto session, Integer mark) {
        this.subgect = subgect;
        this.session = session;
        this.mark = mark;
    }
}
