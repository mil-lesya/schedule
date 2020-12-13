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
    private final SubjectRepository subjectRepository;
    private final AuditoryRepository auditoryRepository;
    private final LecturerRepository lecturerRepository;
    private final CorpusRepository corpusRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, SubjectRepository subjectRepository, AuditoryRepository auditoryRepository, LecturerRepository lecturerRepository, CorpusRepository corpusRepository, GroupRepository groupRepository) {
        this.scheduleRepository = scheduleRepository;
        this.subjectRepository = subjectRepository;
        this.auditoryRepository = auditoryRepository;
        this.lecturerRepository = lecturerRepository;
        this.corpusRepository = corpusRepository;
        this.groupRepository = groupRepository;
    }


    public List<Schedule> getSchedule(Student student) {
        return scheduleRepository.findAllByGroup(student.getGroup());
    }

    public List<Schedule> getGroupSchedule(ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        Group group = groupRepository.findByGroupNumberAndCourse(expectedGroupDto.group, expectedGroupDto.course)
                .orElseThrow(() -> new NoSuchEntityException("группа не найдена"));
        return scheduleRepository.findAllByGroup(group);
    }

    public void saveSchedule(List<ScheduleDto> schedules) throws NoSuchEntityException {
        for (ScheduleDto scheduleDto :
                schedules) {
            Schedule schedule = scheduleRepository.findByClassNumberAndWeek(scheduleDto.classNumber, scheduleDto.week)
                    .orElseThrow(() -> new NoSuchEntityException("расписание не найдено"));

            Subject subject = subjectRepository.findByName(scheduleDto.subject.name)
                    .orElseThrow(() -> new NoSuchEntityException("предмет не найден"));

            Corpus corpus = corpusRepository.findByNumber(scheduleDto.auditory.corpus.number)
                    .orElseThrow(() -> new NoSuchEntityException("корпуса не существует"));

            Auditory auditory = auditoryRepository.findByAuditoryNumberAndCorpus(scheduleDto.auditory.auditoryNumber, corpus)
                    .orElseThrow(() -> new NoSuchEntityException("аудитория не найдена"));
            Lecturer lecturer = lecturerRepository.findByPassPassNumber(scheduleDto.lecturer.passNumber)
                    .orElseThrow(() -> new NoSuchEntityException("лектор не найден"));

            schedule.setAuditory(auditory);
            schedule.setClassNumber(scheduleDto.classNumber);
            schedule.setLecturer(lecturer);
            schedule.setSubject(subject);
            schedule.setWeek(scheduleDto.week);

            scheduleRepository.save(schedule);
        }

    }
}
