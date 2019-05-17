package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository  extends CrudRepository<Session, Long> {
    Optional<Session> findBySemesterNumberAndYear(Integer semesterNumber, Integer year);

}
