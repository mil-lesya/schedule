package com.gmail.mileshko.lesya.schedule.dto;

public class LecturerDto {
    public String passNumber;
    public String surname;
    public String name;
    public String patronymic;
    public String phoneNumber;
    public String email;
    public DepartmentDto department;

    public LecturerDto() {
    }

    public LecturerDto(String passNumber, String surname, String name, String patronymic, String phoneNumber, String email, DepartmentDto department) {
        this.passNumber = passNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
    }
}
