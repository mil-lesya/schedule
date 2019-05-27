package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.AuthStudentDto;
import com.gmail.mileshko.lesya.schedule.dto.NewStudent;
import com.gmail.mileshko.lesya.schedule.dto.RegisterStudentDto;
import com.gmail.mileshko.lesya.schedule.entity.Group;
import com.gmail.mileshko.lesya.schedule.entity.PersonalCard;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.entity.StudentToken;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.AuthorizationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import com.gmail.mileshko.lesya.schedule.security.Hasher;
import com.gmail.mileshko.lesya.schedule.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradebookRepository gradebookRepository;
    private final StudentTokenRepository studentTokenRepository;
    private final GroupRepository groupRepository;
    private final PersonalCardRepository personalCardRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradebookRepository gradebookRepository, StudentTokenRepository studentTokenRepository, GroupRepository groupRepository, PersonalCardRepository personalCardRepository, AssessmentRepository assessmentRepository) {
        this.studentRepository = studentRepository;
        this.gradebookRepository = gradebookRepository;
        this.studentTokenRepository = studentTokenRepository;
        this.groupRepository = groupRepository;
        this.personalCardRepository = personalCardRepository;
    }


    public String authenticate(AuthStudentDto authStudentDto) throws NoSuchEntityException, AuthenticationException {
        Student student = gradebookRepository.findByGradebookNumber(authStudentDto.gradebookNumber)
                .orElseThrow(() -> new NoSuchEntityException("нет студента с таким номером зачётки"))
                .getStudent();
        if (!Hasher.check(authStudentDto.password, student.getPassword())) {
            throw new AuthenticationException("недействительный студент");
        }

        StudentToken token = new StudentToken(student, TokenGenerator.generate());
        return studentTokenRepository.save(token).getToken();
    }

    public Student validate(String tokenValue) throws NoSuchEntityException {
        return studentTokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new NoSuchEntityException("no such token"))
                .getStudent();
    }

    public void register(RegisterStudentDto registerStudentDto) throws RegistrationException {

        Student student = new Student();

        PersonalCard personalCard = new PersonalCard(
                registerStudentDto.surname,
                registerStudentDto.name,
                registerStudentDto.patronymic,
                registerStudentDto.phoneNumber,
                registerStudentDto.parentContact,
                registerStudentDto.address,
                registerStudentDto.mail);

        student.setGradebook(gradebookRepository.findByGradebookNumber(registerStudentDto.gradebookNumber)
                .orElseThrow(() -> new RegistrationException("зачётка не найдена")));
        student.setGroup(groupRepository.findByGroupNumberAndCourse(registerStudentDto.groupNumber, registerStudentDto.course)
                .orElseThrow(() -> new RegistrationException("группа не найдена")));
        student.setPersonalCard(personalCard);
        personalCardRepository.save(personalCard);
        student.setPassword(Hasher.getHash(registerStudentDto.password));
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
