package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.dto.ScheduleDto;
import com.gmail.mileshko.lesya.schedule.entity.*;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final AuditoryRepository auditoryRepository;
    private final LecturerRepository lecturerRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, GroupRepository groupRepository, SubjectRepository subjectRepository, AuditoryRepository auditoryRepository, LecturerRepository lecturerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.auditoryRepository = auditoryRepository;
        this.lecturerRepository = lecturerRepository;
    }


    public List<Schedule> getSchedule(Student student) {
        return scheduleRepository.findAllByGroupList(student.getGroup());
    }

    public List<Schedule> getGroupSchedule(ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        Group group = groupRepository.findByGroupNumberAndCourse(expectedGroupDto.group, expectedGroupDto.course)
                .orElseThrow(() -> new NoSuchEntityException("Группа не найдена"));
        return scheduleRepository.findAllByGroupList(group);
    }

    public void saveSchedule(List<ScheduleDto> schedules) throws NoSuchEntityException {
        for (ScheduleDto scheduleDto :
                schedules) {
            Schedule schedule = scheduleRepository.findBySubjectNameAndWeek(scheduleDto.subject.name, scheduleDto.week)
                    .orElseThrow(()-> new NoSuchEntityException("Расписание не найдено"));

            Subject subject = subjectRepository.findByName(scheduleDto.subject.name)
                    .orElseThrow(() -> new NoSuchEntityException("Предмет не найден"));
            Auditory auditory = auditoryRepository.findByAuditoryNumber(scheduleDto.auditory.auditoryNumber)
                    .orElseThrow(() -> new NoSuchEntityException("Аудитория не найдена"));
            Lecturer lecturer = lecturerRepository.findByPassPassNumber(scheduleDto.lecturer.passNumber)
                    .orElseThrow(()-> new NoSuchEntityException("Лектор не найден"));

            schedule.setAuditory(auditory);
            schedule.setClassNumber(scheduleDto.classNumber);
            schedule.setLecturer(lecturer);
            schedule.setSubject(subject);
            schedule.setWeek(scheduleDto.week);

            scheduleRepository.save(schedule);
        }

    }
}
