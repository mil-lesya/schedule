package com.gmail.mileshko.lesya.schedule.repository;

import com.gmail.mileshko.lesya.schedule.entity.Attendance;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    List<Attendance> findAllByStudentAndPresence(Student student, boolean presence);
}
