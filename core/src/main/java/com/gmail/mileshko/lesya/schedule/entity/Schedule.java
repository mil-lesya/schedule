package com.gmail.mileshko.lesya.schedule.entity;

import com.gmail.mileshko.lesya.schedule.entity.enums.Week;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedule")
public class
Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "auditory_id")
    private Auditory auditory;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Column(name = "class_number")
    private Integer classNumber;

    @Enumerated
    @Column(name = "week")
    private Week week;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Schedule() {
    }

    public Schedule(Subject subject, Auditory auditory, Lecturer lecturer, Integer classNumber, Week week, Group group) {
        this.subject = subject;
        this.auditory = auditory;
        this.lecturer = lecturer;
        this.classNumber = classNumber;
        this.week = week;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public void setAuditory(Auditory auditory) {
        this.auditory = auditory;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
