package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Assessment;
import com.gmail.mileshko.lesya.schedule.entity.Gradebook;
import com.gmail.mileshko.lesya.schedule.entity.Session;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.AuthorizationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.AssessmentRepository;
import com.gmail.mileshko.lesya.schedule.repository.SessionRepository;
import com.gmail.mileshko.lesya.schedule.repository.StudentRepository;
import com.gmail.mileshko.lesya.schedule.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final SessionRepository sessionRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, SessionRepository sessionRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, StudentService studentService) {
        this.assessmentRepository = assessmentRepository;
        this.sessionRepository = sessionRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    public void addAssessment(NewAssessmentDto newAssessmentDto, Student student) throws NoSuchEntityException, AuthorizationException {
        if (!studentService.isHeadman(student))
            throw new AuthorizationException("нет доступа");
        Assessment assessment = new Assessment();
        Gradebook gradebook = studentRepository.findById(newAssessmentDto.studentId)
                .orElseThrow(() -> new NoSuchEntityException("студент не найден"))
                .getGradebook();
        assessment.setGradebook(gradebook);
        Session session;
        Optional<Session> optionalSession = sessionRepository.findBySemesterNumberAndYear(newAssessmentDto.semesterNumber, newAssessmentDto.year);
        if (optionalSession.isPresent()) {
            session = optionalSession.get();
        } else {
            session = new Session(newAssessmentDto.semesterNumber, newAssessmentDto.year);
            sessionRepository.save(session);
        }
        assessment.setSession(session);
        assessment.setSubject(subjectRepository.findByName(newAssessmentDto.subjectName)
                .orElseThrow(() -> new NoSuchEntityException("предмет не найден")));
        assessment.setMark(newAssessmentDto.mark);
        assessmentRepository.save(assessment);

    }

    public void deleteAssessment(Long assessmentId) throws NoSuchEntityException {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new NoSuchEntityException("оценка не найдена"));
        assessmentRepository.delete(assessment);
    }


    public List<Assessment> getGradebookAssessments(Student student) {
        return assessmentRepository.findAllByGradebook(student.getGradebook());
    }


}
