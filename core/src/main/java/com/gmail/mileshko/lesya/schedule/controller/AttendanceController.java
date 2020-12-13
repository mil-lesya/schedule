package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AttendanceDto;
import com.gmail.mileshko.lesya.schedule.dto.NewAttendanceDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.AttendanceService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.service.UserService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("attendance")
public class AttendanceController {
    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final UserService userService;

    @Autowired
    public AttendanceController(StudentService studentService, AttendanceService attendanceService, UserService userService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('STUDENT')")
    public List<AttendanceDto> getAttendance(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Student student = studentService.getStudent(user);
        return Mapper.mapAll(attendanceService.getAttendance(student), AttendanceDto.class);
    }

    @GetMapping("student")
    public List<AttendanceDto> getStudentAttendance(@RequestParam("studentId") Long id) throws NoSuchEntityException {
        Student student = studentService.getStudentById(id);
        return Mapper.mapAll(attendanceService.getAttendance(student), AttendanceDto.class);
    }

    @PostMapping("add")
    @PreAuthorize("hasRole('STUDENT')")
    public void addAttendance(@RequestBody NewAttendanceDto newAttendanceDto, @RequestHeader(HEADER_STRING) String token) throws Throwable {
        User user = userService.getUser(token);
        Student student = studentService.getStudent(user);
        if (studentService.isHeadman(student)) {
            attendanceService.addAttendance(newAttendanceDto);
        }
    }

    @PostMapping("delete")
    @PreAuthorize("hasRole('STUDENT')")
    public void deleteAttendance(@RequestBody Long attendanceId, @RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Student student = studentService.getStudent(user);
        if (studentService.isHeadman(student)) {
            attendanceService.deleteAttendance(attendanceId);
        }
    }
}
