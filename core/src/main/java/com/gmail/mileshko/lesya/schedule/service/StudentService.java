package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.NewStudent;
import com.gmail.mileshko.lesya.schedule.dto.RegisterStudentDto;
import com.gmail.mileshko.lesya.schedule.entity.*;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final PersonalCardRepository personalCardRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GradebookRepository gradebookRepository;
    private final RoleRepository roleRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository, PersonalCardRepository personalCardRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, GradebookRepository gradebookRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.personalCardRepository = personalCardRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.gradebookRepository = gradebookRepository;
        this.roleRepository = roleRepository;
    }

    public void register(RegisterStudentDto registerStudentDto) throws RegistrationException, NoSuchEntityException {

        if (userRepository.findByLogin(registerStudentDto.gradebookNumber).isPresent())
            throw new RegistrationException("пользователь с таким номером зачётки уже существует.");

        Role role = roleRepository.findByRole("STUDENT")
                .orElseThrow(()-> new NoSuchEntityException("роль STUDENT не найдена"));

        User user = new User();
        user.setLogin(registerStudentDto.gradebookNumber);
        user.setPassword(passwordEncoder.encode(registerStudentDto.password));
        user.setRole(role);
        userRepository.save(user);

        Student student = new Student();

        PersonalCard personalCard = new PersonalCard(
                registerStudentDto.surname,
                registerStudentDto.name,
                registerStudentDto.patronymic,
                registerStudentDto.phoneNumber,
                registerStudentDto.parentContact,
                registerStudentDto.address,
                registerStudentDto.email);

        student.setGradebook(gradebookRepository.findByGradebookNumber(registerStudentDto.gradebookNumber)
                .orElseThrow(() -> new RegistrationException("зачётка не найдена")));
        student.setGroup(groupRepository.findByGroupNumberAndCourse(registerStudentDto.groupNumber, registerStudentDto.course)
                .orElseThrow(() -> new RegistrationException("группа не найдена")));
        student.setPersonalCard(personalCard);
        personalCardRepository.save(personalCard);
        student.setUser(user);

        studentRepository.save(student);

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
        Group group = student.getGroup();
        group.setHeadman(student);
        groupRepository.save(group);
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

    public List<Lecturer> searchLecturer(String lecturer) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("SearchLecturer");
        query.setParameter(2, lecturer);
        query.execute();
        @SuppressWarnings("unchecked")
        List<Lecturer> lecturers = query.getResultList();

        return lecturers;
    }
}
