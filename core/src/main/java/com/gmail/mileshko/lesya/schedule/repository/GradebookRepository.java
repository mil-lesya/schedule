package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Gradebook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface GradebookRepository extends CrudRepository<Gradebook, Long> {
    Optional<Gradebook> findByGradebookNumber(String gradebookNumber);
}
