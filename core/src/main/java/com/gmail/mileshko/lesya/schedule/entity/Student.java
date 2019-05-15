package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne
    @JoinColumn(name = "personal_card_id")
    private PersonalCard personalCard;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "gradebook_id")
    private Gradebook gradebook;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    public Student() {
    }

    public Student(Gradebook gradebook, Group group, PersonalCard personalCard, String password, List<Attendance> attendances) {
        this.gradebook = gradebook;
        this.group = group;
        this.personalCard = personalCard;
        this.password = password;
        this.attendances = attendances;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gradebook getGradebook() {
        return gradebook;
    }

    public void setGradebook(Gradebook gradebook) {
        this.gradebook = gradebook;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public PersonalCard getPersonalCard() {
        return personalCard;
    }

    public void setPersonalCard(PersonalCard personalCard) {
        this.personalCard = personalCard;
    }
}
