package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AuthLecturerDto;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth/lecturer")
public class AuthLecturerController {
    private  final LecturerService lecturerService;

    @Autowired
    public AuthLecturerController(LecturerService lecturerService) {

        this.lecturerService = lecturerService;
    }


    @PostMapping
    public String authenticate(@RequestBody AuthLecturerDto authLecturerDto) throws AuthenticationException, NoSuchEntityException{
        return lecturerService.authenticate(authLecturerDto);
    }

}
