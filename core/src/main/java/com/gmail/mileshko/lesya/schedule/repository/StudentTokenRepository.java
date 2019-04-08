package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.StudentToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentTokenRepository extends CrudRepository<StudentToken, Long> {
    Optional<StudentToken> findByToken(String token);
}
