package com.gmail.mileshko.lesya.schedule.dto;

public class AuditoryDto {
    public Integer auditoryNumber;
    public Integer corps;

    public AuditoryDto() {
    }

    public AuditoryDto(Integer auditoryNumber, Integer corps) {
        this.auditoryNumber = auditoryNumber;
        this.corps = corps;
    }
}
