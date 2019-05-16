package com.gmail.mileshko.lesya.schedule.service;

import com.gmail.mileshko.lesya.schedule.dto.ExpectedGroupDto;
import com.gmail.mileshko.lesya.schedule.entity.Group;
import com.gmail.mileshko.lesya.schedule.entity.Schedule;
import com.gmail.mileshko.lesya.schedule.entity.Student;
import com.gmail.mileshko.lesya.schedule.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.schedule.repository.GroupRepository;
import com.gmail.mileshko.lesya.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, GroupRepository groupRepository) {
        this.scheduleRepository = scheduleRepository;
        this.groupRepository = groupRepository;
    }


    public List<Schedule> getSchedule(Student student){
         return scheduleRepository.findAllByGroupList(student.getGroup());
    }

    public List<Schedule> getGroupSchedule(ExpectedGroupDto expectedGroupDto) {
        Group group = groupRepository.findByGroupNumberAndCourse(expectedGroupDto.group, expectedGroupDto.course).get();
        return scheduleRepository.findAllByGroupList(group);
    }
}
