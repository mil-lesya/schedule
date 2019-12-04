package com.gmail.mileshko.lesya.schedule.controller;


import com.gmail.mileshko.lesya.schedule.dto.LecturerDto;
import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.LecturerService;
import com.gmail.mileshko.lesya.schedule.service.UserService;
import com.gmail.mileshko.lesya.schedule.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.HEADER_STRING;

@RestController
    @RequestMapping("lecturer")
    public class LecturerController {
    private final LecturerService lecturerService;
    private final UserService userService;


    @Autowired
    public LecturerController(LecturerService lecturerService, UserService userService) {
        this.lecturerService = lecturerService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('LECTURER')")
    public LecturerDto get(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        Lecturer lecturer = lecturerService.getLecturer(user);
        return Mapper.map(lecturer, LecturerDto.class);
    }

}
