package com.gmail.mileshko.lesya.schedule.controller;


import com.gmail.mileshko.lesya.schedule.dto.LecturerDto;
import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.LecturerService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("feed/lecturer")
    public class LecturerController {
    private final LecturerService lecturerService;

    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public LecturerDto get(@RequestHeader("token") String token) throws NoSuchEntityException {
        Lecturer lecturer = lecturerService.validate(token);
        return Mapper.map(lecturer, LecturerDto.class);
    }
}
