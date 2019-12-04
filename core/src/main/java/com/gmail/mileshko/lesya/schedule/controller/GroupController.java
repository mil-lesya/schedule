package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.GroupService;
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
@RequestMapping("group")
public class GroupController {
    private  final GroupService groupService;
    private final StudentService studentService;
    private final UserService userService;

    @Autowired
    public GroupController(StudentService studentService, GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.userService = userService;
    }

   @GetMapping("get")
   @PreAuthorize("hasRole('STUDENT')")
   public List<StudentDto> getGroup(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
       User user = userService.getUser(token);
       Student student = studentService.getStudent(user);
       return Mapper.mapAll(groupService.getGroup(student), StudentDto.class);
   }

   @PostMapping("expected")
   @PreAuthorize("hasRole('ADMIN')")
   public List<StudentDto> getGroup(@Valid @RequestBody ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
       return Mapper.mapAll(groupService.getExpectedGroup(expectedGroupDto), StudentDto.class);
   }

   @PostMapping("headman")
   @PreAuthorize("hasRole('ADMIN')")
   public Long getHeadmanId(@Valid @RequestBody ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        return groupService.getHeadmanId(expectedGroupDto);
   }

}
