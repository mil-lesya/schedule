package com.gmail.mileshko.lesya.schedule.dto;


import java.time.LocalDate;

public class ClassDto {
    public LocalDate date;
    public ScheduleDto schedule;

    public ClassDto() {
    }

    public ClassDto(LocalDate date, ScheduleDto schedule) {
        this.date = date;
        this.schedule = schedule;
    }
}
