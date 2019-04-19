package com.gmail.mileshko.lesya.schedule.dto;

public class RegisterStudentDto {
    public String name;
    public String surname;
    public String patronymic;
    public String parentContact;
    public String address;
    public String phoneNumber;
    public String mail;
    public String gradebookNumber;
    public Integer groupNumber;
    public Integer course;
    public String password;

    public RegisterStudentDto() {
    }

    public RegisterStudentDto(String name, String surname, String patronymic, String parentContact, String address, String phoneNumber, String mail, String gradebookNumber, Integer groupNumber, Integer course, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.parentContact = parentContact;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.gradebookNumber = gradebookNumber;
        this.groupNumber = groupNumber;
        this.course = course;
        this.password = password;
    }
}
