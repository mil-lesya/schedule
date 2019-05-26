package com.gmail.mileshko.lesya.schedule.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RegisterLecturerDto {

    public String passNumber;

    @NotEmpty(message = "поле \"Фамилия\" не должно быть пустым")
    public String surname;

    @NotEmpty(message = "поле \"Имя\" не должно быть пустым")
    public String name;

    public String patronymic;

    @Pattern(regexp ="^(\\+375)([\\d]{9})$", message = "неверный формат поля \"Номер телефона\"")
    public String phoneNumber;

    @Pattern(regexp ="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z.]{2,6})$", message = "неверный формат поля mail")
    public String mail;

    public Integer auditory;

    @NotEmpty(message = "Поле \"пароль\" не должно быть пустым")
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
