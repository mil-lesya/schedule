package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;
@NamedStoredProcedureQuery(
        name = "SearchSubject",
        procedureName = "search_subject",
        resultClasses = Subject.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class)
        }
)
@Entity
@Table(name="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
