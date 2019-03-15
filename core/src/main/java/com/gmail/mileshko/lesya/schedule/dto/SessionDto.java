package com.gmail.mileshko.lesya.schedule.dto;

public class SessionDto {
    public  Integer semestrNumber;
    public Integer year;

    public SessionDto() {
    }

    public SessionDto(Integer semestrNumber, Integer year) {
        this.semestrNumber = semestrNumber;
        this.year = year;
    }
}
