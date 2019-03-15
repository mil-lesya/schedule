package com.gmail.mileshko.lesya.schedule.dto;

public class AuthLecturerDto {
    public String passNumber;
    public String password;

    public AuthLecturerDto() {
    }

    public AuthLecturerDto(String passNumber, String password) {
        this.passNumber = passNumber;
        this.password = password;
    }
}
