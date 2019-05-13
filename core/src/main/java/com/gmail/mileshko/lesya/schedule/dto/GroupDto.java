package com.gmail.mileshko.lesya.schedule.dto;

import java.util.List;

public class GroupDto {
    public Integer groupNumber;
    public Integer course;
    public Long headmanId;
    public LecturerDto curator;
    public List<ScheduleDto> scheduleList;

    public GroupDto() {
    }

    public GroupDto(Integer groupNumber, Integer course, Long headmanId, LecturerDto curator, List<ScheduleDto> scheduleList) {
        this.groupNumber = groupNumber;
        this.course = course;
        this.headmanId = headmanId;
        this.curator = curator;
        this.scheduleList = scheduleList;
    }
}
