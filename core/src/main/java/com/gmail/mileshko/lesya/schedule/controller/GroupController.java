package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.GroupDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {
    private  final StudentService studentService;

    @Autowired
    public GroupController(StudentService studentService) {
        this.studentService = studentService;
    }


   @GetMapping
   public List<StudentDto> getGroup(@RequestHeader("token") String token) throws NoSuchEntityException {
        Student student = studentService.validate(token);
       return Mapper.mapAll(studentService.getGroup(student), StudentDto.class);

   }

}
