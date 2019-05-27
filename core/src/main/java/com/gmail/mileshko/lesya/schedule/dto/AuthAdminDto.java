package com.gmail.mileshko.lesya.schedule.dto;

import javax.validation.constraints.NotEmpty;

public class AuthAdminDto {

    @NotEmpty(message = "Поле логин не должно быть пустым")
    public String login;

    @NotEmpty(message = "Поле пароль не должно быть пустым")
    public String password;

    public AuthAdminDto() {
    }

    public AuthAdminDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
