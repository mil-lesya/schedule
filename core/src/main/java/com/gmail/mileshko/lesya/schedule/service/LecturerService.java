package com.gmail.mileshko.lesya.schedule.service;


import com.gmail.mileshko.lesya.schedule.dto.RegisterLecturerDto;
import com.gmail.mileshko.lesya.schedule.entity.*;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LecturerService {
    private final LecturerRepository lecturerRepository;
    private final PassRepository passRepository;
    private final AuditoryRepository auditoryRepository;
    private final CorpusRepository corpusRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, PassRepository passRepository, AuditoryRepository auditoryRepository, CorpusRepository corpusRepository, DepartmentRepository departmentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.lecturerRepository = lecturerRepository;
        this.passRepository = passRepository;
        this.auditoryRepository = auditoryRepository;
        this.corpusRepository = corpusRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterLecturerDto registerLecturerDto) throws RegistrationException, NoSuchEntityException {
        if (userRepository.findByLogin(registerLecturerDto.passNumber).isPresent())
            throw new RegistrationException("пользователь с таким номером пропуска уже существует.");

        User user = new User();
        user.setLogin(registerLecturerDto.passNumber);
        user.setPassword(passwordEncoder.encode(registerLecturerDto.password));

        Lecturer lecturer = new Lecturer();
        Pass pass = passRepository.findByPassNumber(registerLecturerDto.passNumber)
                .orElseThrow(() -> new NoSuchEntityException("пропуск с таким номером не cуществует"));
        lecturer.setPass(pass);
        lecturer.setName(registerLecturerDto.name);
        lecturer.setSurname(registerLecturerDto.surname);
        lecturer.setPatronymic(registerLecturerDto.patronymic);
        lecturer.setPhoneNumber(registerLecturerDto.phoneNumber);
        lecturer.setEmail(registerLecturerDto.email);

        Corpus corpus = corpusRepository.findByNumber(registerLecturerDto.corpus)
                .orElseThrow(()-> new NoSuchEntityException("корпуса не существует"));
        Auditory auditory = auditoryRepository.findByAuditoryNumberAndCorpus(registerLecturerDto.auditory, corpus)
                .orElseThrow(() -> new NoSuchEntityException("нет аудитории с таким номером"));

        lecturer.setDepartment(departmentRepository.findByAuditory(auditory)
                        .orElseThrow(() -> new NoSuchEntityException("кафедры не существует")));
        lecturer.setUser(user);

        lecturerRepository.save(lecturer);
        userRepository.save(user);
    }

    public Lecturer getLecturer(User user) throws NoSuchEntityException {
        return lecturerRepository.findByUser(user)
                .orElseThrow(()-> new NoSuchEntityException("студент не найден"));
    }
}
