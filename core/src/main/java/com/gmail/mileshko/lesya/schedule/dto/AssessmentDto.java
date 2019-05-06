package com.gmail.mileshko.lesya.schedule.dto;

public class AssessmentDto {
    public GradebookDto gradebook;
    public SubjectDto subject;
    public SessionDto session;
    public Integer mark;

    public AssessmentDto() {
    }

    public AssessmentDto(GradebookDto gradebook, SubjectDto subject, SessionDto session, Integer mark) {
        this.gradebook = gradebook;
        this.subject = subject;
        this.session = session;
        this.mark = mark;
    }
}
