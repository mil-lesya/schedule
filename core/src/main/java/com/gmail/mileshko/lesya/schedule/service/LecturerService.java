package com.gmail.mileshko.lesya.schedule.service;


import com.gmail.mileshko.lesya.schedule.dto.AuthLecturerDto;
import com.gmail.mileshko.lesya.schedule.dto.RegisterLecturerDto;
import com.gmail.mileshko.lesya.schedule.entity.Department;
import com.gmail.mileshko.lesya.schedule.entity.Lecturer;
import com.gmail.mileshko.lesya.schedule.entity.LecturerToken;
import com.gmail.mileshko.lesya.schedule.entity.Pass;
import com.gmail.mileshko.lesya.schedule.exception.AuthenticationException;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.exception.RegistrationException;
import com.gmail.mileshko.lesya.schedule.repository.*;
import com.gmail.mileshko.lesya.schedule.security.Hasher;
import com.gmail.mileshko.lesya.schedule.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {
    private final LecturerRepository lecturerRepository;
    private final PassRepository passRepository;
    private final LecturerTokenRepository lecturerTokenRepository;
    private final AuditoryRepository auditoryRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, PassRepository passRepository, LecturerTokenRepository lecturerTokenRepository, AuditoryRepository auditoryRepository, DepartmentRepository departmentRepository) {
        this.lecturerRepository = lecturerRepository;
        this.passRepository = passRepository;
        this.lecturerTokenRepository = lecturerTokenRepository;
        this.auditoryRepository = auditoryRepository;
        this.departmentRepository = departmentRepository;
    }

    public String authenticate(AuthLecturerDto authLecturerDto) throws NoSuchEntityException, AuthenticationException {
        Lecturer lecturer = passRepository.findByPassNumber(authLecturerDto.passNumber)
                .orElseThrow(() -> new NoSuchEntityException("no lecturer with such pass number"))
                .getLecturer();
        if (!Hasher.check(authLecturerDto.password, lecturer.getPassword()))
            throw new AuthenticationException("invalid lecturer");//

        LecturerToken token = new LecturerToken(lecturer, TokenGenerator.generate());
        return lecturerTokenRepository.save(token).getToken();
    }

    public Lecturer validate(String tokenValue) throws NoSuchEntityException {
        return lecturerTokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new NoSuchEntityException("no such token"))
                .getLecturer();
    }


    public void register(RegisterLecturerDto registerLecturerDto) throws RegistrationException {

        Lecturer lecturer = new Lecturer();
        Pass pass = passRepository.findByPassNumber(registerLecturerDto.passNumber)
                .orElseThrow(() -> new RegistrationException("lecturer with the pass number doesn't exist"));
        pass.setName(registerLecturerDto.name);
        pass.setName(registerLecturerDto.surname);
        pass.setName(registerLecturerDto.patronymic);
        passRepository.save(pass);

        lecturer.setPass(pass);
        lecturer.setName(registerLecturerDto.name);
        lecturer.setSurname(registerLecturerDto.surname);
        lecturer.setPatronymic(registerLecturerDto.patronymic);
        lecturer.setPhoneNumber(registerLecturerDto.phoneNumber);
        lecturer.setMail(registerLecturerDto.mail);
        lecturer.setDepartment(departmentRepository.findByAuditory(auditoryRepository.findByAuditoryNumber(registerLecturerDto.auditory)
                .orElseThrow(() -> new RegistrationException("the entered auditory doesn't exist")))
                .orElseThrow(() -> new RegistrationException("department doesn't exist")));
        lecturer.setPassword(Hasher.getHash(registerLecturerDto.password));
        lecturerRepository.save(lecturer);
    }
}
