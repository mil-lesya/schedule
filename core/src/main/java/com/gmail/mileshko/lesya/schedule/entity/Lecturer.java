package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "department_id")
    private Department departmentId;

    @Column(name = "pass_number")
    private Long passNumber;

    @Column(name = "surname")
    private  String surname;

    @Column(name = "name")
    private  String name;

    @Column(name = "patronimic")
    private String patronimic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    public Lecturer() {
    }

    public Lecturer(Department departmentId, Long passNumber, String surname, String name, String patronimic, String phoneNumber, String mail) {
        this.departmentId = departmentId;
        this.passNumber = passNumber;
        this.surname = surname;
        this.name = name;
        this.patronimic = patronimic;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Long getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Long passNumber) {
        this.passNumber = passNumber;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
