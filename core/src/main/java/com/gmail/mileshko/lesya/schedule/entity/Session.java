package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="semester_number")
    private Integer semesterNumber;

    @Column(name="year")
    private Integer year;

    public Session(Integer semesterNumber, Integer year) {
        this.semesterNumber = semesterNumber;
        this.year = year;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
