package com.gmail.mileshko.lesya.schedule.dto;

public class StudentDto {
    public GradebookDto gradebook;
    public GroupDto group;
    public PersonalCardDto personalCard;

    public StudentDto() {
    }

    public StudentDto(GradebookDto gradebook, GroupDto group, PersonalCardDto personalCard) {
        this.gradebook = gradebook;
        this.group = group;
        this.personalCard = personalCard;
    }
}
