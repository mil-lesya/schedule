package com.gmail.mileshko.lesya.schedule.dto;

import org.hibernate.validator.constraints.Range;

public class ExpectedGroupDto {

    @Range(min = 1, max = 10, message = "недопустимый номер  группы")
    public Integer group;

    @Range(min = 1, max = 10, message = "недопустимый курс")
    public Integer course;

    public ExpectedGroupDto() {
    }

    public ExpectedGroupDto(Integer group, Integer course) {
        this.group = group;
        this.course = course;
    }
}
