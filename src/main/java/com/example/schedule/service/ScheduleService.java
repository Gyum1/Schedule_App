package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setAuthor(requestDto.getAuthor());
        schedule.setPassword(requestDto.getPassword());

        scheduleRepository.save(schedule);
    }
}
