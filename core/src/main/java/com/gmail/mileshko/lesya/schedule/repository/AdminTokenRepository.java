package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.AdminToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminTokenRepository extends CrudRepository<AdminToken, Long> {
    Optional<AdminToken> findByToken(String token);
}
