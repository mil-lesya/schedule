package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "auditory")
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "auditory_number")
    private Integer auditoryNumber;


    public Auditory() {
    }

    public Auditory(Integer auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setAuditoryNumber(Integer auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }
}
