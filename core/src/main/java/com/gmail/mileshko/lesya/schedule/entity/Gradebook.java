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
    private Integer gradebookNumber;

    @OneToMany
    @JoinColumn(name="assessment_id")
    private List<Assessment> assessment;

    public Gradebook() {
    }

    public Gradebook(Integer gradebookNumber, List<Assessment> assessment) {
        this.gradebookNumber = gradebookNumber;
        this.assessment = assessment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGradebookNumber() {
        return gradebookNumber;
    }

    public void setGradebookNumber(Integer gradebookNumber) {
        this.gradebookNumber = gradebookNumber;
    }

    public List<Assessment> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<Assessment> assessment) {
        this.assessment = assessment;
    }
}
