package com.gmail.mileshko.lesya.schedule.dto;

public class SessionDto {
    public  Integer semesterNumber;
    public Integer year;

    public SessionDto() {
    }

    public SessionDto(Integer semesterNumber, Integer year) {
        this.semesterNumber = semesterNumber;
        this.year = year;
    }
}
