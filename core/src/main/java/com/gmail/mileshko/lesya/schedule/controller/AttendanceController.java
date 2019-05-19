package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
import com.gmail.mileshko.lesya.schedule.dto.AttendanceDto;
import com.gmail.mileshko.lesya.schedule.dto.NewAttendanceDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.StudentRepository;
import com.gmail.mileshko.lesya.schedule.service.AttendanceService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attendance")
public class AttendanceController {
    private  final StudentService studentService;
    private final AttendanceService attendanceService;
    private final StudentRepository studentRepository;


    @Autowired
    public AttendanceController(StudentService studentService, AttendanceService attendanceService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.studentRepository = studentRepository;
    }


    @GetMapping("get")
    public List<AttendanceDto> getAttendance(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.mapAll(attendanceService.getAttendance(student), AttendanceDto.class);

    }

    @GetMapping("student")
    public List<AttendanceDto> getStudentAttendance(@RequestParam("studentId") Long id) throws NoSuchEntityException {
        Student student = studentRepository.findById(id).orElseThrow(()-> new NoSuchEntityException("Студент не найден") );
        return Mapper.mapAll(attendanceService.getAttendance(student), AttendanceDto.class);
    }


    @PostMapping("add")
    public void addAttendance(@RequestBody NewAttendanceDto newAttendanceDto) throws NoSuchEntityException {
        attendanceService.addAttendance(newAttendanceDto);
    }

    @PostMapping("delete")
    public void deleteAttendance(@RequestBody Long attendanceId) throws NoSuchEntityException {
        attendanceService.deleteAttendance(attendanceId);
    }
}
