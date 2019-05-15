package com.gmail.mileshko.lesya.schedule.dto;

public class PassDto {
    public  LecturerDto lecturer;
    public  String passNumber;
    public String surname;
    public String name;
    public  String patronymic;

    public PassDto() {
    }

    public PassDto(LecturerDto lecturer, String passNumber, String surname, String name, String patronymic) {
        this.lecturer = lecturer;
        this.passNumber = passNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
}
