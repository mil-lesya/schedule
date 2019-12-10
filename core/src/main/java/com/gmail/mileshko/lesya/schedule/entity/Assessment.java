package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "GetStudentAssessment",
        procedureName = "get_student_assessments",
        resultClasses = Assessment.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)
        }
)
@Entity
@Table(name = "assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gradebook_id")
    private Gradebook gradebook;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(name = "mark")
    private Integer mark;

    public Assessment() {
    }

    public Assessment(Gradebook gradebook, Subject subject, Session session, Integer mark) {
        this.gradebook = gradebook;
        this.subject = subject;
        this.session = session;
        this.mark = mark;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Gradebook getGradebook() {
        return gradebook;
    }

    public void setGradebook(Gradebook gradebook) {
        this.gradebook = gradebook;
    }
}
