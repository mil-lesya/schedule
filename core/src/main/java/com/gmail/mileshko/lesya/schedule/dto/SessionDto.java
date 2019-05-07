package com.gmail.mileshko.lesya.schedule.dto;

public class SessionDto {
    public  Integer semesterNumber;
    public String year;

    public SessionDto() {
    }

    public SessionDto(Integer semesterNumber, String year) {
        this.semesterNumber = semesterNumber;
        this.year = year;
    }
}
