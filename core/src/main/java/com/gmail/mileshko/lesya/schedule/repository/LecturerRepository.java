package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface LecturerRepository extends CrudRepository<Lecturer, Long> {
    Optional<Lecturer> findByPassPassNumber(String passNumber);
    Optional<Lecturer> findByUser(User user);
}
