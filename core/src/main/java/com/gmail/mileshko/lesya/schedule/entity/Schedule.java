package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @JoinColumn(name = "auditory_id")
    private Auditory auditoryId;

    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturerId;

    @Column(name = "class_number")
    private Integer classNumber;

    @Column(name = "periodicity")
    private Integer periodicity;

    @Column(name = "week")
    private  String week;

    public Schedule() {
    }

    public Schedule(Subject subjectId, Auditory auditoryId, Lecturer lecturerId, Integer classNumber, Integer periodicity, String week) {
        this.subjectId = subjectId;
        this.auditoryId = auditoryId;
        this.lecturerId = lecturerId;
        this.classNumber = classNumber;
        this.periodicity = periodicity;
        this.week = week;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Auditory getAuditoryId() {
        return auditoryId;
    }

    public void setAuditoryId(Auditory auditoryId) {
        this.auditoryId = auditoryId;
    }

    public Lecturer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Lecturer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Integer periodicity) {
        this.periodicity = periodicity;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
