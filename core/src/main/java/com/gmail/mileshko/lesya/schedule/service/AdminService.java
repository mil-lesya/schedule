package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AuthAdminDto;
import com.gmail.mileshko.lesya.schedule.dto.AuthLecturerDto;
import com.gmail.mileshko.lesya.schedule.entity.AdminToken;
import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AdminTokenRepository;
import com.gmail.mileshko.lesya.schedule.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
@Transactional
public class AdminService {
   private final AdminTokenRepository adminTokenRepository;

   @Autowired
    public AdminService(AdminTokenRepository adminTokenRepository) {
        this.adminTokenRepository = adminTokenRepository;
    }

    public String authenticate(AuthAdminDto authAdminDto) throws AuthenticationException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("admin.properties");
        assert inputStream != null;
        properties.load(inputStream);
        if (!(authAdminDto.login.equals(properties.getProperty("login")) && authAdminDto.password.equals(properties.getProperty("password"))))
            throw new AuthenticationException("неверный логин или пароль");
        AdminToken token = new AdminToken(TokenGenerator.generate());
        return adminTokenRepository.save(token).getToken();
    }

    public void validate(String tokenValue) throws AuthenticationException {
        if(!adminTokenRepository.findByToken(tokenValue).isPresent())
            throw new AuthenticationException("нет доступа");
    }
}
