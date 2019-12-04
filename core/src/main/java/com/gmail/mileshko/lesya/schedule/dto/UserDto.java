package com.gmail.mileshko.lesya.schedule.dto;


public class UserDto {

    public String login;
    public String password;
    public RoleDto role;

    public UserDto() {
    }

    public UserDto(String login, String password, RoleDto role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
