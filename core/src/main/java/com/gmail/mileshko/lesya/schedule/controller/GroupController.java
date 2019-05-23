package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.GroupService;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {
    private  final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public GroupController(StudentService studentService, GroupService groupService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }


   @GetMapping("get")
   public List<StudentDto> getGroup(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
       return Mapper.mapAll(groupService.getGroup(student), StudentDto.class);

   }

   @PostMapping
   public List<StudentDto> getGroup(@Valid @RequestBody ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
       return Mapper.mapAll(groupService.getExpectedGroup(expectedGroupDto), StudentDto.class);
   }

   @PostMapping("headman")
    public Long getHeadmanId(@Valid @RequestBody ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        return groupService.getHeadmanId(expectedGroupDto);
   }

}
