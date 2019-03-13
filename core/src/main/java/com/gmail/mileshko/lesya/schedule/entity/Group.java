package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "headman_id")
    private Student headman;

    @OneToOne
    @JoinColumn(name = "curator_id")
    private Lecturer curator;

    @ManyToMany
    @JoinTable(
            name = "group_schedule",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Schedule> scheduleList;

    public Group() {
    }

    public Group(Integer groupNumber, Integer course, Student headman, Lecturer curator) {
        this.groupNumber = groupNumber;
        this.course = course;
        this.headman = headman;
        this.curator = curator;
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

    public Student getHeadman() {
        return headman;
    }

    public void setHeadman(Student headman) {
        this.headman = headman;
    }

    public Lecturer getCurator() {
        return curator;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void setCurator(Lecturer curator) {
        this.curator = curator;
    }
}
