package com.gmail.mileshko.lesya.schedule.dto;

import javax.validation.constraints.NotNull;

public class AuthLecturerDto {
    @NotNull
    public String passNumber;

    @NotNull
    public String password;

    public AuthLecturerDto() {
    }

    public AuthLecturerDto(String passNumber, String password) {
        this.passNumber = passNumber;
        this.password = password;
    }
}
