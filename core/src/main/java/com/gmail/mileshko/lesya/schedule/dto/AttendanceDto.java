package com.gmail.mileshko.lesya.schedule.dto;

public class AttendanceDto {
    public Long id;
    public  StudentDto student;
    public ClassDto _class;
    public  Boolean preseance;

    public AttendanceDto() {
    }

    public AttendanceDto(StudentDto student, ClassDto _class, Boolean preseance) {
        this.student = student;
        this._class = _class;
        this.preseance = preseance;
    }

    public AttendanceDto(Long id, StudentDto student, ClassDto _class, Boolean preseance) {
        this.id = id;
        this.student = student;
        this._class = _class;
        this.preseance = preseance;
    }
}
