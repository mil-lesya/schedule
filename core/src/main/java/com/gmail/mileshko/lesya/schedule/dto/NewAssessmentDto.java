package com.gmail.mileshko.lesya.schedule.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewAssessmentDto {

    @Range(min = 1, max = 8, message = "недопустимый номер семестра")
    public Integer semesterNumber;

    public String subjectName;

    @Range(min = 1, max = 10, message = "недопустимая оценка")
    public Integer mark;

    public Integer year;

    public  Long studentId;

    public NewAssessmentDto() {
    }

    public NewAssessmentDto(Integer semesterNumber, String subjectName, Integer mark, Integer year, Long studentId) {
        this.semesterNumber = semesterNumber;
        this.subjectName = subjectName;
        this.mark = mark;
        this.year = year;
        this.studentId = studentId;
    }
}
