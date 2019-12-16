package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.dto.ScheduleDto;
import com.gmail.mileshko.lesya.schedule.entity.*;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SubjectRepository subjectRepository;
    private final AuditoryRepository auditoryRepository;
    private final LecturerRepository lecturerRepository;
    private final CorpusRepository corpusRepository;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, SubjectRepository subjectRepository, AuditoryRepository auditoryRepository, LecturerRepository lecturerRepository, CorpusRepository corpusRepository) {
        this.scheduleRepository = scheduleRepository;
        this.subjectRepository = subjectRepository;
        this.auditoryRepository = auditoryRepository;
        this.lecturerRepository = lecturerRepository;
        this.corpusRepository = corpusRepository;
    }

    public List<Schedule> getSchedule(Student student) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Schedule.GetSchedule");
        query.setParameter(2, student.getGroup().getId());
        query.execute();
        @SuppressWarnings("unchecked")
        List<Schedule> schedules = query.getResultList();
        return schedules;
    }

    public List<Schedule> getGroupSchedule(ExpectedGroupDto expectedGroupDto) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("GetGroupSchedule");
        query.setParameter(2, expectedGroupDto.group);
        query.setParameter(3, expectedGroupDto.course);
        query.execute();
        @SuppressWarnings("unchecked")
        List<Schedule> schedules = query.getResultList();
        return schedules;
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
