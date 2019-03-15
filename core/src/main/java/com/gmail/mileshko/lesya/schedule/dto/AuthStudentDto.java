package com.gmail.mileshko.lesya.schedule.dto;

public class AuthStudentDto {

    public String gradebookNumber;
    public String password;

    public AuthStudentDto() {
    }

    public AuthStudentDto(String gradebookNumber, String password) {
        this.gradebookNumber = gradebookNumber;
        this.password = password;
    }
}
