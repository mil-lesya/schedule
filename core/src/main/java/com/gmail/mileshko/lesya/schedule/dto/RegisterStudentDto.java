package com.gmail.mileshko.lesya.schedule.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RegisterStudentDto {
    @NotEmpty(message = "поле \"Имя\" не должно быть пустым")
    public String name;

    @NotEmpty(message = "поле \"Фамилия\" не должно быть пустым")
    public String surname;
    public String patronymic;

    @Pattern(regexp ="^(\\+375)([\\d]{9})$", message = "неверный формат поля \"Номер телефона родителя\"")
    public String parentContact;
    public String address;

    @Pattern(regexp ="^(\\+375)([\\d]{9})$", message = "неверный формат поля \"Номер телефона\"")
    public String phoneNumber;

    @Pattern(regexp ="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z.]{2,6})$", message = "неверный формат поля mail")
    public String mail;
    public String gradebookNumber;

    @Range(min = 1, max = 10, message = "несуществующий номер группы")
    public Integer groupNumber;

    @Range(min = 1, max = 4, message = "несуществующий курс")
    public Integer course;

    @NotEmpty(message = "Поле \"пароль\" не должно быть пустым")
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
