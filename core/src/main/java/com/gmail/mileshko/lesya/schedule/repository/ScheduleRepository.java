package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Group;
import com.gmail.mileshko.lesya.schedule.entity.Schedule;
import com.gmail.mileshko.lesya.schedule.entity.enums.Week;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByGroup(Group group);
    Optional<Schedule> findBySubjectNameAndWeekAndClassNumber(String subjectName, Week week, Integer classNumber);
    Optional<Schedule> findByClassNumberAndWeek(Integer classNumber, Week week);
}