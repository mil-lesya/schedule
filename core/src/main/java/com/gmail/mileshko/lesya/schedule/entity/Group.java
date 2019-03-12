package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_number")
    private Integer groupNumber;

    @Column(name = "course")
    private Integer course;

    @JoinColumn(name = "headman_id")
    private Student headmanId;

    @JoinColumn(name = "curator_id")
    private Lecturer curatorId;

    public Group() {
    }

    public Group(Integer groupNumber, Integer course, Student headmanId, Lecturer curatorId) {
        this.groupNumber = groupNumber;
        this.course = course;
        this.headmanId = headmanId;
        this.curatorId = curatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Student getHeadmanId() {
        return headmanId;
    }

    public void setHeadmanId(Student headmanId) {
        this.headmanId = headmanId;
    }

    public Lecturer getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(Lecturer curatorId) {
        this.curatorId = curatorId;
    }
}
