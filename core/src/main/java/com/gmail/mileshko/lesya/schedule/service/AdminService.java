package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AdminDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
@Transactional
public class AdminService {
    public boolean authorize(AdminDto adminDto) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("admin.properties");
        properties.load(inputStream);
        return adminDto.login.equals(properties.getProperty("login")) && adminDto.password.equals(properties.getProperty("password"));
    }
}
