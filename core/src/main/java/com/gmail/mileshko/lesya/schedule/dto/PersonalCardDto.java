package com.gmail.mileshko.lesya.schedule.dto;

public class PersonalCardDto {

    private String name;
    private String surname;
    private String patronymic;
    private String parentContact;
    private String address;
    private String phoneNumber;
    private String mail;

    public PersonalCardDto() {
    }

    public PersonalCardDto(String name, String surname, String patronymic, String parentContact, String address, String phoneNumber, String mail) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.parentContact = parentContact;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }
}
