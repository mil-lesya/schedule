package com.gmail.mileshko.lesya.schedule.dto;

import javax.validation.constraints.NotNull;

public class AdminDto {

    @NotNull
    public String login;

    @NotNull
    public String password;

    public AdminDto() {
    }

    public AdminDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
