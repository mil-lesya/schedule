package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AssessmentDto;
import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Assessment;
import com.gmail.mileshko.lesya.schedule.entity.Gradebook;
import com.gmail.mileshko.lesya.schedule.entity.Session;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.*;
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
    private final StudentRepository studentRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, SessionRepository sessionRepository, SubjectRepository subjectRepository, GradebookRepository gradebookRepository, StudentRepository studentRepository) {
        this.assessmentRepository = assessmentRepository;
        this.sessionRepository = sessionRepository;
        this.subjectRepository = subjectRepository;
        this.gradebookRepository = gradebookRepository;
        this.studentRepository = studentRepository;
    }


    public void addAssessment(NewAssessmentDto newAssessmentDto) throws NoSuchEntityException {

        Assessment assessment = new Assessment();
        Gradebook gradebook = studentRepository.findById(newAssessmentDto.studentId)
                .orElseThrow(() -> new NoSuchEntityException("Студент не найден"))
                .getGradebook();
        assessment.setGradebook(gradebook);
        Session session;
        if (sessionRepository.findBySemesterNumberAndYear(newAssessmentDto.semesterNumber, newAssessmentDto.year).isPresent()) {
            session = sessionRepository.findBySemesterNumberAndYear(newAssessmentDto.semesterNumber, newAssessmentDto.year).get();
        }
        else {
            session = new Session(newAssessmentDto.semesterNumber, newAssessmentDto.year);
            sessionRepository.save(session);
        }
        assessment.setSession(session);
        assessment.setSubject(subjectRepository.findByName(newAssessmentDto.subjectName).orElseThrow(() -> new NoSuchEntityException("Предмет не найден")));
        assessment.setMark(newAssessmentDto.mark);
        assessmentRepository.save(assessment);

    }

    public void deleteAssessment(Long assessmentId) throws NoSuchEntityException {
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(() -> new NoSuchEntityException("Оценка не найдена"));
        assessmentRepository.delete(assessment);
    }


    public List<Assessment> getGradebookAssessments(Student student) {
        return assessmentRepository.findAllByGradebook(student.getGradebook());
    }


}
