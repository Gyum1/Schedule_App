package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public String createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        scheduleService.createSchedule(requestDto);
        return "일정이 성공적으로 등록되었습니다.";
    }
}
