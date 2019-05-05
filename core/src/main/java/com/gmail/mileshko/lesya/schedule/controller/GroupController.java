package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
   public List<Student> getGroup(Student student){
        return studentService.getGroup(student);
   }
}
