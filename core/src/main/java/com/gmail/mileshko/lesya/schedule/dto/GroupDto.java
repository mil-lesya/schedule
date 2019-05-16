package com.gmail.mileshko.lesya.schedule.dto;

public class GroupDto {
    public Integer groupNumber;
    public Integer course;
    public LecturerDto curator;

    public GroupDto() {
    }

    public GroupDto(Integer groupNumber, Integer course, LecturerDto curator) {
        this.groupNumber = groupNumber;
        this.course = course;
        this.curator = curator;
    }
}
