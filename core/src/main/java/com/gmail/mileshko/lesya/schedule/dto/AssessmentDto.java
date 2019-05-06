package com.gmail.mileshko.lesya.schedule.dto;

public class AssessmentDto {
    public SubjectDto subject;
    public SessionDto session;
    public Integer mark;

    public AssessmentDto() {
    }

    public AssessmentDto(SubjectDto subject, SessionDto session, Integer mark) {
        this.subject = subject;
        this.session = session;
        this.mark = mark;
    }
}
