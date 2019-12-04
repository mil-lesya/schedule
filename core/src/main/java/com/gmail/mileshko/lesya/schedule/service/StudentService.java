package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.NewStudent;
import com.gmail.mileshko.lesya.schedule.dto.RegisterStudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Group;
import com.gmail.mileshko.lesya.schedule.entity.PersonalCard;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final PersonalCardRepository personalCardRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository, PersonalCardRepository personalCardRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.personalCardRepository = personalCardRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterStudentDto registerStudentDto) throws RegistrationException {
        if (userRepository.findByLogin(registerStudentDto.gradebookNumber).isPresent())
            throw new RegistrationException("пользователь с таким номером зачётки уже существует.");

        StoredProcedureQuery query = em.createStoredProcedureQuery("register_student");
        query.registerStoredProcedureParameter("login", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("password", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("address", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("email", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("pname", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("parent_contact", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("patronymic", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("phone_number", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("surname", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("group_number", Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("course", Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("first", Integer.class, ParameterMode.OUT);
        query.setParameter("login", registerStudentDto.gradebookNumber);
        query.setParameter("password", passwordEncoder.encode(registerStudentDto.password));
        query.setParameter("address", registerStudentDto.address);
        query.setParameter("email", registerStudentDto.email);
        query.setParameter("pname", registerStudentDto.name);
        query.setParameter("parent_contact", registerStudentDto.parentContact);
        query.setParameter("patronymic", registerStudentDto.patronymic);
        query.setParameter("phone_number", registerStudentDto.phoneNumber);
        query.setParameter("surname", registerStudentDto.surname);
        query.setParameter("group_number", registerStudentDto.groupNumber);
        query.setParameter("course", registerStudentDto.course);
        Object result = query.getOutputParameterValue("first");
    }

    public Boolean isHeadman(Student student) {
        return groupRepository.findByHeadman(student).isPresent();
    }

    public void deleteStudent(Long studentId) throws NoSuchEntityException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchEntityException("студент не найден"));

        studentRepository.delete(student);
    }

    public void appointHeadman(Long headmanId) throws NoSuchEntityException {
        Student student = studentRepository.findById(headmanId)
                .orElseThrow(() -> new NoSuchEntityException("студент не найден"));
        student.getGroup().setHeadman(student);
    }

    public void changeGroup(NewStudent newStudent) throws NoSuchEntityException {
        Group group = groupRepository.findByGroupNumberAndCourse(newStudent.group, newStudent.course)
                .orElseThrow(() -> new NoSuchEntityException("группа не найдена"));
        PersonalCard personalCard = personalCardRepository.findBySurnameAndNameAndPatronymic(newStudent.surname, newStudent.name, newStudent.patronymic)
                .orElseThrow(() -> new NoSuchEntityException("персональная карта не найдена"));
        Student student = studentRepository.findByPersonalCard(personalCard)
                .orElseThrow(() -> new NoSuchEntityException("студент не найден"));

        student.setGroup(group);
        studentRepository.save(student);

    }

    public Student getStudent(User user) throws NoSuchEntityException {
        return studentRepository.findByUser(user)
                .orElseThrow(()-> new NoSuchEntityException("студент не найден"));
    }

    public Student getStudentById(Long id) throws NoSuchEntityException {
        return studentRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("студент не найден"));
    }
}
