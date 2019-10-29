package com.gmail.mileshko.lesya.schedule.dto;


public class UserDto {

    private String login;
    private String password;

    public UserDto() {
    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
