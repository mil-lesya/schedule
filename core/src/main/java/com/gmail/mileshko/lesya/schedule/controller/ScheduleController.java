package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.dto.ScheduleDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.ScheduleService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.service.UserService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final StudentService studentService;
    private final UserService userService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, StudentService studentService, UserService userService) {
        this.scheduleService = scheduleService;
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('STUDENT')")
    public List<ScheduleDto> getSchedule(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Student student = studentService.getStudent(user);
        return Mapper.mapAll(scheduleService.getSchedule(student), ScheduleDto.class);
    }

    @PostMapping("group")
    @PreAuthorize("hasAnyRole('ADMIN','LECTURER')")
    public List<ScheduleDto> getGroupSchedule(@Valid @RequestBody ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        return Mapper.mapAll(scheduleService.getGroupSchedule(expectedGroupDto), ScheduleDto.class);
    }

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveSchedule(@RequestBody List<ScheduleDto> schedules) throws NoSuchEntityException {
        scheduleService.saveSchedule(schedules);
    }
}

