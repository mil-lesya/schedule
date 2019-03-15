package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.LecturerDto;
import com.gmail.mileshko.lesya.schedule.dto.StudentDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegisterService {


    public void register(StudentDto studentDto) {
    }

    public void register(LecturerDto lecturerDto) {
    }
}
