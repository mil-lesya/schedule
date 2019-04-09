package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AuthStudentDto;
import com.gmail.mileshko.lesya.schedule.dto.RegisterStudentDto;
import com.gmail.mileshko.lesya.schedule.entity.*;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import com.gmail.mileshko.lesya.schedule.security.Hasher;
import com.gmail.mileshko.lesya.schedule.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradebookRepository gradebookRepository;
    private final StudentTokenRepository studentTokenRepository;
    private final GroupRepository groupRepository;
    private final PersonalCardRepository personalCardRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradebookRepository gradebookRepository, StudentTokenRepository studentTokenRepository, GroupRepository groupRepository, PersonalCardRepository personalCardRepository) {
        this.studentRepository = studentRepository;
        this.gradebookRepository = gradebookRepository;
        this.studentTokenRepository = studentTokenRepository;
        this.groupRepository = groupRepository;
        this.personalCardRepository = personalCardRepository;
    }

    public String authenticate(AuthStudentDto authStudentDto) throws NoSuchEntityException, AuthenticationException {
        Student student = gradebookRepository.findByGradebookNumber(authStudentDto.gradebookNumber)
                .orElseThrow(() -> new NoSuchEntityException("no student with such gradebook number"))
                .getStudent();
        if (!Hasher.check(authStudentDto.password, student.getPassword()))
            throw new AuthenticationException("invalid student");//

        StudentToken token = new StudentToken(student, TokenGenerator.generate());
        return studentTokenRepository.save(token).getToken();
    }

    public Student validate(String tokenValue) throws NoSuchEntityException {
        return studentTokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new NoSuchEntityException("no such token"))
                .getStudent();
    }

    public void register(RegisterStudentDto registerStudentDto) throws RegistrationException {
        if (gradebookRepository.findByGradebookNumber(registerStudentDto.gradebook).isPresent())
            throw new RegistrationException("student with such gradebook number already exist");

        Student student = new Student();
        Group group = new Group(
                registerStudentDto.groupNumber,
                registerStudentDto.course);
        PersonalCard personalCard = new PersonalCard(
                registerStudentDto.surname,
                registerStudentDto.name,
                registerStudentDto.patronymic,
                registerStudentDto.phoneNumber,
                registerStudentDto.parentContact,
                registerStudentDto.address,
                registerStudentDto.mail);

        student.setGradebook(new Gradebook(registerStudentDto.gradebook));

        student.setGroup(group);
        groupRepository.save(group);
        student.setPersonalCard(personalCard);
        personalCardRepository.save(personalCard);
        student.setPassword(Hasher.getHash(registerStudentDto.password));
        studentRepository.save(student);
    }
}
