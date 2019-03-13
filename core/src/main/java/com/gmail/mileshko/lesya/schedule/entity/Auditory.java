package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "auditory")
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "corps")
    private  Integer corps;

    @Column(name = "auditory_number")
    private Long auditoryNumber;

    public Auditory() {
    }

    public Auditory(Integer corps, Long auditoryNumber) {
        this.corps = corps;
        this.auditoryNumber = auditoryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCorps() {
        return corps;
    }

    public void setCorps(Integer corps) {
        this.corps = corps;
    }

    public Long getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setAuditoryNumber(Long auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }
}
