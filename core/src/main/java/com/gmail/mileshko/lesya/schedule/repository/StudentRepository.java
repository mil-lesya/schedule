package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAllByGroupOrderByPersonalCardSurname(Group group);
    Optional<Student> findByPersonalCard(PersonalCard personalCard);
    Optional<Student> findByUser(User user);
}