package com.gmail.mileshko.lesya.schedule.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class NewStudent {
    @NotNull
    public String surname;

    @NotNull
    public String name;
    public String patronymic;

    @Range(min=1, max=4, message = "Неверный курс")
    public Integer course;

    @Range(min=1, max=10, message = "Неверный номер группы")
    public Integer group;

    public NewStudent() {
    }

    public NewStudent(  String surname, String name, String patronymic, Integer course, Integer group) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.course = course;
        this.group = group;
    }
}
