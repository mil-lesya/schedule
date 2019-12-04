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
    public String email;

    public String auditory;

    public String corpus;

    @NotEmpty(message = "Поле \"пароль\" не должно быть пустым")
    public String password;

    public RegisterLecturerDto() {
    }

    public RegisterLecturerDto(String passNumber, String surname, String name, String patronymic, String phoneNumber, String email, String auditory, String corpus, String password) {
        this.passNumber = passNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.auditory = auditory;
        this.corpus = corpus;
        this.password = password;
    }
}
