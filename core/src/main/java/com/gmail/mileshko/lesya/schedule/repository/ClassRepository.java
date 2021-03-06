package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Class;
import com.gmail.mileshko.lesya.schedule.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {
    Optional<Class> findByDateAndSchedule(LocalDate Date, Schedule schedule);
}
