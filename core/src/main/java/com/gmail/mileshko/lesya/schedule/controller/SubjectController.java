package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.SubjectDto;
import com.gmail.mileshko.lesya.schedule.service.SubjectService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {
    private  final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SubjectDto> searchSubject(@RequestParam("subject")String subject) {
        return Mapper.mapAll(subjectService.searchSubject(subject), SubjectDto.class);
    }
}
