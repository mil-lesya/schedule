package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "auditory")
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "auditory_number")
    private String auditoryNumber;

    @ManyToOne
    @JoinColumn(name = "corpus_id")
    private Corpus corpus;


    public Auditory() {
    }

    public Auditory(String auditoryNumber, Corpus corpus) {
        this.auditoryNumber = auditoryNumber;
        this.corpus = corpus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }

    public Corpus getCorpus() {
        return corpus;
    }

    public void setCorpus(Corpus corpus) {
        this.corpus = corpus;
    }
}
