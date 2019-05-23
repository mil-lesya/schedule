package com.gmail.mileshko.lesya.schedule.dto;

import javax.validation.constraints.NotEmpty;

public class AuthStudentDto {

    @NotEmpty(message = "Поле \"Номер зачётки\" не должно быть пустым")
    public String gradebookNumber;

    @NotEmpty(message = "Поле \"пароль\" не должно быть пустым")
    public String password;

    public AuthStudentDto() {
    }

    public AuthStudentDto(String gradebookNumber, String password) {
        this.gradebookNumber = gradebookNumber;
        this.password = password;
    }
}
