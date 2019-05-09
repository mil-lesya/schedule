package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
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

import java.util.List;

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

    public void editAssessment(List<AssessmentDto> assessmentsDto) throws NoSuchEntityException, AuthorizationException {

        for (AssessmentDto assessmentDto :
                assessmentsDto) {
            Assessment assessment = assessmentRepository.findById(assessmentDto.id).orElseThrow(()-> new NoSuchEntityException(""));
            assessment.setSession(sessionRepository.findBySemesterNumberAndYear(assessmentDto.session.semesterNumber, assessmentDto.session.year).orElseThrow(()-> new NoSuchEntityException("")));

            assessment.setSubject(subjectRepository.findByName(assessmentDto.subject.name).orElseThrow(()-> new NoSuchEntityException("")));
            assessment.setMark(assessmentDto.mark);
            assessmentRepository.save(assessment);
        }

    }
}
