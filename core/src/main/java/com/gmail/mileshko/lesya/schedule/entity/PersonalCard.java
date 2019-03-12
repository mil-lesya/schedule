package com.gmail.mileshko.lesya.schedule.entity;


import javax.persistence.*;

@Entity
@Table(name = "personal_card")
public class PersonalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname")
    private  String surname;

    @Column(name = "name")
    private  String name;

    @Column(name = "patronimic")
    private String patronimic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "parent_contact")
    private String parentContact;

    @Column(name = "address")
    private String address;

    @Column(name = "mail")
    private String mail;

    public PersonalCard() {
    }

    public PersonalCard(String surname, String name, String patronimic, String phoneNumber, String parentContact, String address, String mail) {
        this.surname = surname;
        this.name = name;
        this.patronimic = patronimic;
        this.phoneNumber = phoneNumber;
        this.parentContact = parentContact;
        this.address = address;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronimic() {
        return patronimic;
    }

    public void setPatronimic(String patronimic) {
        this.patronimic = patronimic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getParentContact() {
        return parentContact;
    }

    public void setParentContact(String parentContact) {
        this.parentContact = parentContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
