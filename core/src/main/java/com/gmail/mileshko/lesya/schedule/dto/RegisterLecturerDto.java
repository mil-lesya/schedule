package com.gmail.mileshko.lesya.schedule.dto;

public class RegisterLecturerDto {
    public String passNumber;
    public String surname;
    public String name;
    public String patronymic;
    public String phoneNumber;
    public String mail;
    public Integer auditory;
    public String password;

    public RegisterLecturerDto() {
    }

    public RegisterLecturerDto(String passNumber, String surname, String name, String patronymic, String phoneNumber, String mail, Integer auditory, String password) {
        this.passNumber = passNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.auditory = auditory;
        this.password = password;
    }
}
