package com.gmail.mileshko.lesya.schedule.dto;

public class AuditoryDto {

    public String auditoryNumber;
    public CorpusDto corpus;


    public AuditoryDto() {
    }

    public AuditoryDto(String auditoryNumber, CorpusDto corpus) {
        this.auditoryNumber = auditoryNumber;
        this.corpus = corpus;
    }
}
