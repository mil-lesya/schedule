package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "personal_card")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "gradebook_id")
    private Gradebook gradebookId;

    @JoinColumn(name = "group_id")
    private Group groupId;

    @JoinColumn(name = "personal_card_id")
    private PersonalCard personalCardId;

    public Student() {
    }

    public Student(Gradebook gradebookId, Group groupId, PersonalCard personalCardId) {
        this.gradebookId = gradebookId;
        this.groupId = groupId;
        this.personalCardId = personalCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gradebook getGradebookId() {
        return gradebookId;
    }

    public void setGradebookId(Gradebook gradebookId) {
        this.gradebookId = gradebookId;
    }

    public Group getGroupId() {
        return groupId;
    }

    public void setGroupId(Group groupId) {
        this.groupId = groupId;
    }

    public PersonalCard getPersonalCardId() {
        return personalCardId;
    }

    public void setPersonalCardId(PersonalCard personalCardId) {
        this.personalCardId = personalCardId;
    }
}
