package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Assessment;
import com.gmail.mileshko.lesya.schedule.entity.Gradebook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentRepository extends CrudRepository<Assessment, Long> {

    List<Assessment> findAllByGradebook(Gradebook gradebook);


}
