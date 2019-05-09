package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.EditAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Assessment;
import com.gmail.mileshko.lesya.schedule.entity.Session;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthorizationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AssessmentRepository;
import com.gmail.mileshko.lesya.schedule.repository.SessionRepository;
import com.gmail.mileshko.lesya.schedule.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final StudentService studentService;
    private final SessionRepository sessionRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, StudentService studentService, SessionRepository sessionRepository, SubjectRepository subjectRepository) {
        this.assessmentRepository = assessmentRepository;
        this.studentService = studentService;
        this.sessionRepository = sessionRepository;
        this.subjectRepository = subjectRepository;
    }

    public Assessment editAssessment(Assessment assessment, EditAssessmentDto editAssessmentDto) throws NoSuchEntityException, AuthorizationException {


        assessment.setSubject(subjectRepository.findByName(editAssessmentDto.subjectName).orElseThrow(()-> new NoSuchEntityException("нет предмета с таким названием")));
        Session session = sessionRepository.findBySemesterNumberAndYear(editAssessmentDto.semesterNumber, editAssessmentDto.year).orElseThrow(()-> new NoSuchEntityException("нет сессии с таким номером и годом обучения"));
        assessment.setSession(session);
        assessment.setMark(editAssessmentDto.mark);

        return assessmentRepository.save(assessment);
    }
}
