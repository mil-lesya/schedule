package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "corpus")
public class Corpus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;


    public Corpus() {
    }

    public Corpus(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
