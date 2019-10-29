package com.gmail.mileshko.lesya.schedule.service;


import com.gmail.mileshko.lesya.schedule.dto.RegisterLecturerDto;
import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.entity.Pass;
import com.gmail.mileshko.lesya.schedule.entity.User;
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
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, PassRepository passRepository, AuditoryRepository auditoryRepository, DepartmentRepository departmentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.lecturerRepository = lecturerRepository;
        this.passRepository = passRepository;
        this.auditoryRepository = auditoryRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterLecturerDto registerLecturerDto) throws RegistrationException {
        if (userRepository.findByLogin(registerLecturerDto.passNumber).isPresent())
            throw new RegistrationException("пользователь с таким номером пропуска уже существует.");

        User user = new User();
        user.setLogin(registerLecturerDto.passNumber);
        user.setEmail(registerLecturerDto.email);
        user.setPassword(passwordEncoder.encode(registerLecturerDto.password));
        userRepository.save(user);

        Lecturer lecturer = new Lecturer();
        Pass pass = passRepository.findByPassNumber(registerLecturerDto.passNumber)
                .orElseThrow(() -> new RegistrationException("нет преподавателя с таким номером пропуска"));
        lecturer.setPass(pass);
        lecturer.setName(registerLecturerDto.name);
        lecturer.setSurname(registerLecturerDto.surname);
        lecturer.setPatronymic(registerLecturerDto.patronymic);
        lecturer.setPhoneNumber(registerLecturerDto.phoneNumber);
        lecturer.setDepartment(
                departmentRepository.findByAuditory(auditoryRepository.findByAuditoryNumber(registerLecturerDto.auditory)
                .orElseThrow(() -> new RegistrationException("нет аудитории с таким номером")))
                        .orElseThrow(() -> new RegistrationException("кафедры не существует")));
        lecturer.setUser(user);
        lecturerRepository.save(lecturer);
    }
}
