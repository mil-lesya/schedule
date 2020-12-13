package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.NewAttendanceDto;
import com.gmail.mileshko.lesya.schedule.entity.Attendance;
import com.gmail.mileshko.lesya.schedule.entity.Class;
import com.gmail.mileshko.lesya.schedule.entity.Schedule;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.enums.Week;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AttendanceRepository;
import com.gmail.mileshko.lesya.schedule.repository.ClassRepository;
import com.gmail.mileshko.lesya.schedule.repository.ScheduleRepository;
import com.gmail.mileshko.lesya.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final ScheduleRepository scheduleRepository;


    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository, ClassRepository classRepository, ScheduleRepository scheduleRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Attendance> getAttendance(Student student) {
        return attendanceRepository.findAllByStudentAndPresence(student, false);
    }


    public void addAttendance(NewAttendanceDto newAttendanceDto) throws NoSuchEntityException {
        Attendance attendance = new Attendance();
        int date = newAttendanceDto.dateClass.getDayOfWeek().getValue() - 1;
        Week week = Week.values()[date];
        Schedule schedule = scheduleRepository.findBySubjectNameAndWeekAndClassNumber(newAttendanceDto.subjectName, week, newAttendanceDto.classNumber - 1)
                .orElseThrow(() -> new NoSuchEntityException("не найдено расписание с таким днём недели и названием предмета"));
        Optional<Class> optionalClass = classRepository.findByDateAndSchedule(newAttendanceDto.dateClass, schedule);
       // Class _class = optionalClass.orElseGet(() -> new Class(newAttendanceDto.dateClass, schedule));

        Class _class = null;
        if (optionalClass.isPresent()) {
            _class = optionalClass.get();
        } else {
            _class = new Class(newAttendanceDto.dateClass, schedule);
            classRepository.save(_class);
        }
        attendance.set_class(_class);
        attendance.setStudent(studentRepository.findById(newAttendanceDto.studentId)
                .orElseThrow(() -> new NoSuchEntityException("студент не найден")));
        attendance.setPresence(false);
        attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long attendanceId) throws NoSuchEntityException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new NoSuchEntityException("посещение не найдено"));
        attendanceRepository.delete(attendance);
    }
}
