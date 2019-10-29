package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.SECRET;
import static com.gmail.mileshko.lesya.schedule.security.SecurityConstants.TOKEN_PREFIX;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String token) throws NoSuchEntityException {
        String login = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return userRepository.findByLogin(login)
                .orElseThrow(()-> new NoSuchEntityException("no such user."));
    }
}
