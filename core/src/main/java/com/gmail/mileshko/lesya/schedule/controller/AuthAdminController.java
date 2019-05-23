package com.gmail.mileshko.lesya.schedule.controller;

import com.gmail.mileshko.lesya.schedule.dto.AdminDto;
import com.gmail.mileshko.lesya.schedule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("admin")
public class AuthAdminController {
    private  final AdminService adminService;

    @Autowired
    public AuthAdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("auth")
    public boolean authorize(@Valid @RequestBody AdminDto adminDto) throws  IOException {
        return adminService.authorize(adminDto);
    }
}
