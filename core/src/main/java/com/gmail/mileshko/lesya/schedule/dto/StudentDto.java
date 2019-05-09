package com.gmail.mileshko.lesya.schedule.dto;

public class StudentDto {
    public Long id;
    public GroupDto group;
    public PersonalCardDto personalCard;

    public StudentDto() {
    }

    public StudentDto(Long id, GroupDto group, PersonalCardDto personalCard) {
        this.id = id;

        this.group = group;
        this.personalCard = personalCard;
    }
}
