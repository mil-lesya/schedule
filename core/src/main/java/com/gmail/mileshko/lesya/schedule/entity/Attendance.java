package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;
@NamedStoredProcedureQuery(
        name = "GetAttendance",
        procedureName = "get_attendance",
        resultClasses = Attendance.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)
        }
)
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "class_id")
    private Class _class;

    public Attendance() {
    }

    public Attendance(Student student, Class _class) {
        this.student = student;
        this._class = _class;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }
}
