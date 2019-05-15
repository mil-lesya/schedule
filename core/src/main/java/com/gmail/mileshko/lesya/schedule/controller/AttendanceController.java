package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AttendanceDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.AttendanceService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("attendance")
public class AttendanceController {
    private  final StudentService studentService;
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(StudentService studentService, AttendanceService attendanceService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
    }


    @GetMapping("get")
    public List<AttendanceDto> getGroup(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.mapAll(attendanceService.getAttendance(student), AttendanceDto.class);

    }
}
