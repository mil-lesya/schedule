package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.LecturerToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerTokenRepository extends CrudRepository<LecturerToken, Long> {

        Optional<LecturerToken> findByToken(String token);

}
