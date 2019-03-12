package com.gmail.mileshko.lesya.schedule.entity;

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

    @JoinColumn(name="assessment_id")
    private  Assessment assessmentId;

    public Gradebook() {
    }

    public Gradebook(Integer gradebookNumber, Assessment assessmentId) {
        this.gradebookNumber = gradebookNumber;
        this.assessmentId = assessmentId;
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

    public Assessment getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Assessment assessmentId) {
        this.assessmentId = assessmentId;
    }
}
