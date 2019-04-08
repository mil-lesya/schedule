package com.gmail.mileshko.lesya.schedule.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="gradebook")
public class Gradebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name="gradebook_number")
    private String gradebookNumber;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany
    @JoinColumn(name="ASSESSMENT_ID")
    private List<Assessment> assessment;

    public Gradebook() {
    }

    public Gradebook(String gradebookNumber) {
        this.gradebookNumber = gradebookNumber;
    }

    public Gradebook(String gradebookNumber, List<Assessment> assessment) {
        this.gradebookNumber = gradebookNumber;
        this.assessment = assessment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradebookNumber() {
        return gradebookNumber;
    }

    public void setGradebookNumber(String gradebookNumber) {
        this.gradebookNumber = gradebookNumber;
    }

    public List<Assessment> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<Assessment> assessment) {
        this.assessment = assessment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
