package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

}
