package com.gmail.mileshko.lesya.schedule.dto;

public class GroupDto {
    public Integer groupNumber;
    public Integer course;
    public Long headmanId;
    public LecturerDto curator;

    public GroupDto() {
    }
    
	public GroupDto(Integer groupNumber, Integer course, Long headmanId, LecturerDto curator) {
		this.groupNumber = groupNumber;
		this.course = course;
		this.headmanId = headmanId;
		this.curator = curator;
	}
}
