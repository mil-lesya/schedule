package com.gmail.mileshko.lesya.schedule.dto;

public class PersonalCardDto {

    public String name;
    public String surname;
    public String patronymic;
    public String parentContact;
    public String address;
    public String phoneNumber;
    public String mail;

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
