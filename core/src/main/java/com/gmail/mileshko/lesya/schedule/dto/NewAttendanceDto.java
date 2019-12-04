package com.gmail.mileshko.lesya.schedule.dto;

import java.time.LocalDate;

public class NewAttendanceDto {
    public Long studentId;
    public LocalDate dateClass;
    public String subjectName;
    public Integer classNumber;

    public NewAttendanceDto() {
    }

    public NewAttendanceDto(Long studentId, LocalDate dateClass, String subjectName, Integer classNumber) {
        this.studentId = studentId;
        this.dateClass = dateClass;
        this.subjectName = subjectName;
        this.classNumber = classNumber;
    }
}
