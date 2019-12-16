package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@Transactional
public class GroupService {
    @PersistenceContext
    EntityManager em;

    public List<Student> getGroup(Student student) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("GetGroup");
        query.setParameter(2, student.getGroup().getId());
        query.execute();
        @SuppressWarnings("unchecked")
        List<Student> group = query.getResultList();
        return group;
    }

    public List<Student> getExpectedGroup(ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("GetExpectedGroup");
        query.setParameter(2, expectedGroupDto.group);
        query.setParameter(3, expectedGroupDto.course);
        query.execute();
        @SuppressWarnings("unchecked")
        List<Student> group = query.getResultList();
        return group;
    }

    public Long getHeadmanId(ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        StoredProcedureQuery query = em.createStoredProcedureQuery("get_headman_id");
        query.registerStoredProcedureParameter("f_group", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("f_course", Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("f_headman_id", Long.class, ParameterMode.OUT);
        query.setParameter("f_group", expectedGroupDto.group);
        query.setParameter("f_course", expectedGroupDto.course);
        Object headmanId = query.getOutputParameterValue("f_headman_id");
        return (Long) headmanId;
    }

}
