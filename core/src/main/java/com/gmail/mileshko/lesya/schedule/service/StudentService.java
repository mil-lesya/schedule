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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradebookRepository gradebookRepository;
    private final GroupRepository groupRepository;
    private final PersonalCardRepository personalCardRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradebookRepository gradebookRepository, GroupRepository groupRepository, PersonalCardRepository personalCardRepository, AssessmentRepository assessmentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.gradebookRepository = gradebookRepository;
        this.groupRepository = groupRepository;
        this.personalCardRepository = personalCardRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterStudentDto registerStudentDto) throws RegistrationException {
        if (userRepository.findByLogin(registerStudentDto.gradebookNumber).isPresent())
            throw new RegistrationException("пользователь с таким номером зачётки уже существует.");

        User newUser = new User();
        newUser.setLogin(registerStudentDto.gradebookNumber);
        newUser.setEmail(registerStudentDto.email);
        newUser.setPassword(passwordEncoder.encode(registerStudentDto.password));
        User user = userRepository.save(newUser);

        Student student = new Student();

        PersonalCard personalCard = personalCardRepository.save(new PersonalCard(
                registerStudentDto.surname,
                registerStudentDto.name,
                registerStudentDto.patronymic,
                registerStudentDto.phoneNumber,
                registerStudentDto.parentContact,
                registerStudentDto.address
        ));

        student.setGradebook(gradebookRepository.findByGradebookNumber(registerStudentDto.gradebookNumber)
                .orElseThrow(() -> new RegistrationException("зачётка не найдена")));
        student.setGroup(groupRepository.findByGroupNumberAndCourse(registerStudentDto.groupNumber, registerStudentDto.course)
                .orElseThrow(() -> new RegistrationException("группа не найдена")));
        student.setPersonalCard(personalCard);
        student.setUser(user);

        studentRepository.save(student);
    }


    public Boolean isHeadman(Student student) {
        return groupRepository.findByHeadman(student).isPresent();
    }


    public void saveStudent(NewStudent newStudent) throws NoSuchEntityException {
        Group group = groupRepository.findByGroupNumberAndCourse(newStudent.group, newStudent.course)
                .orElseThrow(() -> new NoSuchEntityException("группа не найдена"));
        PersonalCard personalCard = personalCardRepository.findBySurnameAndNameAndPatronymic(newStudent.surname, newStudent.name, newStudent.patronymic)
                .orElseThrow(() -> new NoSuchEntityException("персональная карта не найдена"));
        Student student = studentRepository.findByPersonalCard(personalCard)
                .orElseThrow(() -> new NoSuchEntityException("студент не найден"));

        student.setGroup(group);
        studentRepository.save(student);

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

}
