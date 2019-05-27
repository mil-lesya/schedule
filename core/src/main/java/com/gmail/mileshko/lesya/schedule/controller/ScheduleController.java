package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.dto.ScheduleDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.AdminService;
import com.gmail.mileshko.lesya.schedule.service.ScheduleService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final StudentService studentService;
    private final AdminService adminService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, StudentService studentService, AdminService adminService) {
        this.scheduleService = scheduleService;
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @GetMapping("get")
    public List<ScheduleDto> getSchedule(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
        return Mapper.mapAll(scheduleService.getSchedule(student), ScheduleDto.class);
    }

    @PostMapping("group")
    public List<ScheduleDto> getGroupSchedule(@Valid @RequestBody ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        return Mapper.mapAll(scheduleService.getGroupSchedule(expectedGroupDto), ScheduleDto.class);
    }

    @PostMapping("save")
    public void saveSchedule(@RequestBody List<ScheduleDto> schedules, @RequestHeader("token") String token) throws NoSuchEntityException, AuthenticationException {
        adminService.validate(token);
        scheduleService.saveSchedule(schedules);
    }
}

