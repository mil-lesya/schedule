package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Assessment;
import com.gmail.mileshko.lesya.schedule.exception.AuthorizationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AssessmentRepository;
import com.gmail.mileshko.lesya.schedule.repository.GradebookRepository;
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
    private final SessionRepository sessionRepository;
    private final SubjectRepository subjectRepository;
    private final GradebookRepository gradebookRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, SessionRepository sessionRepository, SubjectRepository subjectRepository, GradebookRepository gradebookRepository) {
        this.assessmentRepository = assessmentRepository;
        this.sessionRepository = sessionRepository;
        this.subjectRepository = subjectRepository;
        this.gradebookRepository = gradebookRepository;
    }

    public void editAssessment(List<AssessmentDto> assessmentsDto) throws NoSuchEntityException{

        Assessment assessment = new Assessment();
        for (AssessmentDto assessmentDto :
                assessmentsDto) {
            assessment.setSession(sessionRepository.findBySemesterNumberAndYear(assessmentDto.session.semesterNumber, assessmentDto.session.year).orElseThrow(() -> new NoSuchEntityException("")));
            assessment.setSubject(subjectRepository.findByName(assessmentDto.subject.name).orElseThrow(() -> new NoSuchEntityException("")));
            assessment.setMark(assessmentDto.mark);
            assessmentRepository.save(assessment);
        }
    }

    public void addAssessment(List<NewAssessmentDto> newAssessmentsDto) throws NoSuchEntityException {

        Assessment assessment = new Assessment();
        for (NewAssessmentDto newAssessmentDto :
                newAssessmentsDto) {
            assessment.setGradebook(gradebookRepository.findByGradebookNumber(newAssessmentDto.gradebook.gradebookNumber).orElseThrow(() -> new NoSuchEntityException("")));
            assessment.setSession(sessionRepository.findBySemesterNumberAndYear(newAssessmentDto.semesterNumber, newAssessmentDto.year).orElseThrow(() -> new NoSuchEntityException("")));
            assessment.setSubject(subjectRepository.findByName(newAssessmentDto.subjectName).orElseThrow(() -> new NoSuchEntityException("")));
            assessment.setMark(newAssessmentDto.mark);
            assessmentRepository.save(assessment);
        }

    }


}
