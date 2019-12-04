package com.gmail.mileshko.lesya.schedule.dto;

import com.gmail.mileshko.lesya.schedule.entity.Auditory;

import java.util.List;

public class CorpusDto {
    public String number;
    public List<Auditory> auditoriums;

    public CorpusDto() {
    }

    public CorpusDto(String number, List<Auditory> auditoriums) {
        this.number = number;
        this.auditoriums = auditoriums;
    }
}
