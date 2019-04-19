package com.gmail.mileshko.lesya.schedule.dto;

public class LecturerDto {
    public String passNumber;
    public String surname;
    public String name;
    public String patronymic;
    public String phoneNumber;
    public String mail;
    public DepartmentDto department;

    public LecturerDto() {
    }

    public LecturerDto(String passNumber, String surname, String name, String patronymic, String phoneNumber, String mail, DepartmentDto department) {
        this.passNumber = passNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.department = department;
    }
}