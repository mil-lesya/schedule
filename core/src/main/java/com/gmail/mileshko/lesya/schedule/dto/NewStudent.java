package com.gmail.mileshko.lesya.schedule.dto;

public class NewStudent {
    public String surname;
    public String name;
    public String patronymic;
    public Integer course;
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
