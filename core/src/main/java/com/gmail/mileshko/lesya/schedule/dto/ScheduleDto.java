package com.gmail.mileshko.lesya.schedule.dto;

import com.gmail.mileshko.lesya.schedule.entity.enums.Week;

import java.util.List;

public class ScheduleDto {
    public Long id;
    public SubjectDto subject;
    public AuditoryDto auditory;
    public LecturerDto lecturer;
    public Integer classNumber;
    public Week week;

    public ScheduleDto() {
    }

    public ScheduleDto(SubjectDto subject, AuditoryDto auditory, LecturerDto lecturer, Integer classNumber, Week week) {
        this.subject = subject;
        this.auditory = auditory;
        this.lecturer = lecturer;
        this.classNumber = classNumber;
        this.week = week;
    }

    public ScheduleDto(Long id, SubjectDto subject, AuditoryDto auditory, LecturerDto lecturer, Integer classNumber, Week week) {
        this.id = id;
        this.subject = subject;
        this.auditory = auditory;
        this.lecturer = lecturer;
        this.classNumber = classNumber;
        this.week = week;
    }
}
