package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "group_schedule")
public class GroupSchedule {

    @JoinColumn(name = "shedule_id")
    private Schedule ScheduleId;

    @JoinColumn(name = "group_id")
    private Group groupId;

    public GroupSchedule() {
    }

    public GroupSchedule(Schedule scheduleId, Group groupId) {
        ScheduleId = scheduleId;
        this.groupId = groupId;
    }

    public Schedule getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(Schedule scheduleId) {
        ScheduleId = scheduleId;
    }

    public Group getGroupId() {
        return groupId;
    }

    public void setGroupId(Group groupId) {
        this.groupId = groupId;
    }
}
