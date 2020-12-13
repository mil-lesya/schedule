package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.entity.Group;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.GroupRepository;
import com.gmail.mileshko.lesya.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<Student> getGroup(Student student) {
        return studentRepository.findAllByGroupOrderByPersonalCardSurname(student.getGroup());
    }

    public List<Student> getExpectedGroup(ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        Group group = groupRepository.findByGroupNumberAndCourse(expectedGroupDto.group, expectedGroupDto.course)
                .orElseThrow(() -> new NoSuchEntityException("несуществующая группа"));
        return studentRepository.findAllByGroupOrderByPersonalCardSurname(group);
    }

    public Long getHeadmanId(ExpectedGroupDto expectedGroupDto) throws NoSuchEntityException {
        Group group = groupRepository.findByGroupNumberAndCourse(expectedGroupDto.group, expectedGroupDto.course)
                .orElseThrow(() -> new NoSuchEntityException("несуществующая группа"));
        return group.getHeadman().getId();

    }

}