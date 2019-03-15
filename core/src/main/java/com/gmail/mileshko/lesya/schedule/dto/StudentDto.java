package com.gmail.mileshko.lesya.schedule.dto;

public class StudentDto {
    public GradebookDto gradebookDto;
    public GroupDto groupDto;
    public PersonalCardDto personalCardDto;

    public StudentDto() {
    }

    public StudentDto(GradebookDto gradebookDto, GroupDto groupDto, PersonalCardDto personalCardDto) {
        this.gradebookDto = gradebookDto;
        this.groupDto = groupDto;
        this.personalCardDto = personalCardDto;
    }
}
