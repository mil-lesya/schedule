package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.entity.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@Transactional
public class SubjectService {
    @PersistenceContext
    EntityManager em;

    public List<Subject> searchSubject(String subject) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("SearchSubject");
        query.setParameter(2, subject);
        query.execute();
        @SuppressWarnings("unchecked")
        List<Subject> subjects = query.getResultList();
        return subjects;
    }
}
