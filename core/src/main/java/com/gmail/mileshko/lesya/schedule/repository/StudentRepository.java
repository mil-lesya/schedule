package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Gradebook;
import com.gmail.mileshko.lesya.schedule.entity.Group;
import com.gmail.mileshko.lesya.schedule.entity.PersonalCard;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAllByGroupOrderByPersonalCardSurname(Group group);
    Optional<Student> findByPersonalCard(PersonalCard personalCard);
    Optional<Student> findByGradebook(Gradebook gradebook);
}
