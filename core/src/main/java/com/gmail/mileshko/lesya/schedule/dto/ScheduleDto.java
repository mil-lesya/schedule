package com.gmail.mileshko.lesya.schedule.dto;

import com.gmail.mileshko.lesya.schedule.entity.enums.Week;

import java.util.List;

public class ScheduleDto {
    public SubjectDto subjectDto;
    public AuditoryDto auditoryDto;
    public LecturerDto lecturerDto;
    public Integer classNumber;
    public Integer periodicity;
    public Week week;

    public ScheduleDto() {
    }

    public ScheduleDto(SubjectDto subjectDto, AuditoryDto auditoryDto, LecturerDto lecturerDto, Integer classNumber, Integer periodicity, Week week) {
        this.subjectDto = subjectDto;
        this.auditoryDto = auditoryDto;
        this.lecturerDto = lecturerDto;
        this.classNumber = classNumber;
        this.periodicity = periodicity;
        this.week = week;
    }
}
