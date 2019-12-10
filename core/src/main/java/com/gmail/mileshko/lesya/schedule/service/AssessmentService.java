package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.NewAssessmentDto;
import com.gmail.mileshko.lesya.schedule.entity.Assessment;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@Transactional
public class AssessmentService {
    @PersistenceContext
    EntityManager em;

    public void addAssessment(NewAssessmentDto newAssessmentDto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("add_assessment");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, Long.class, ParameterMode.IN);
        query.setParameter(1, newAssessmentDto.semesterNumber);
        query.setParameter(2, newAssessmentDto.subjectName);
        query.setParameter(3, newAssessmentDto.mark);
        query.setParameter(4, newAssessmentDto.year);
        query.setParameter(5, newAssessmentDto.studentId);
        Object assessmentId = query.getSingleResult();
    }

    public void deleteAssessment(Long assessmentId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("delete_assessment");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.setParameter(1, assessmentId);
        Object assessment = query.getSingleResult();
    }

    public List<Assessment> getGradebookAssessments(Student student) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("GetStudentAssessment");
        query.setParameter(2, student.getGradebook().getId());
        query.execute();
        @SuppressWarnings("unchecked")
        List<Assessment> attendances = query.getResultList();
        return attendances;
    }
}
