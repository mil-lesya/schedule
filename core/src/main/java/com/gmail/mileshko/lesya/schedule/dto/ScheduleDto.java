package com.gmail.mileshko.lesya.schedule.dto;

public class ScheduleDto {
    public SubjectDto subjectDto;
    public AuditoryDto auditoryDto;
    public LecturerDto lecturerDto;
    public Integer classNumber;
    public Integer pereodicity;
    public String week;

    public ScheduleDto() {
    }

    public ScheduleDto(SubjectDto subjectDto, AuditoryDto auditoryDto, LecturerDto lecturerDto, Integer classNumber, Integer pereodicity, String week) {
        this.subjectDto = subjectDto;
        this.auditoryDto = auditoryDto;
        this.lecturerDto = lecturerDto;
        this.classNumber = classNumber;
        this.pereodicity = pereodicity;
        this.week = week;
    }
}
