package com.gmail.mileshko.lesya.schedule.dto;

public class GroupDto {
    public Integer groupNumber;
    public Integer course;
    public StudentDto headman;
    public LecturerDto curator;

    public GroupDto() {
    }

    public GroupDto(Integer groupNumber, Integer course, StudentDto headman, LecturerDto curator) {
        this.groupNumber = groupNumber;
        this.course = course;
        this.headman = headman;
        this.curator = curator;
    }
}
