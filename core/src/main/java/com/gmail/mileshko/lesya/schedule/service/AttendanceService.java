package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.NewAttendanceDto;
import com.gmail.mileshko.lesya.schedule.entity.Attendance;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAttendance(Student student) {
        return attendanceRepository.findAllByStudentAndPresence(student, false);
    }


    public void addAttendance(NewAttendanceDto newAttendanceDto) {

        Integer date = newAttendanceDto.dateClass.getDayOfWeek().getValue() - 1;

        StoredProcedureQuery query = em.createStoredProcedureQuery("add_attendance");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, LocalDate.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
        query.setParameter(1, newAttendanceDto.studentId);
        query.setParameter(2, newAttendanceDto.dateClass);
        query.setParameter(3, newAttendanceDto.subjectName);
        query.setParameter(4, date);
        query.setParameter(5, newAttendanceDto.classNumber);

        Object attendance = query.getSingleResult();
    }

    public void deleteAttendance(Long attendanceId) throws NoSuchEntityException {
        StoredProcedureQuery query = em.createStoredProcedureQuery("delete_attendance");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.setParameter(1, attendanceId);

        Object attendance = query.getSingleResult();
    }
}
