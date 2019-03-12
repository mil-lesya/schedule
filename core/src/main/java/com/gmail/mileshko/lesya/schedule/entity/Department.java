package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "auditory_id")
    private Auditory auditoryId;

    public Department() {
    }

    public Department(Auditory auditoryId) {
        this.auditoryId = auditoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Auditory getAuditoryId() {
        return auditoryId;
    }

    public void setAuditoryId(Auditory auditoryId) {
        this.auditoryId = auditoryId;
    }
}
