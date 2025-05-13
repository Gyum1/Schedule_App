package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.SchedulePasswordDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 1. 일정 생성
    @PostMapping
    public String createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        scheduleService.createSchedule(requestDto);
        return "일정이 성공적으로 등록되었습니다.";
    }

    // 2. 전체 일정 조회
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 3. 단건 일정 조회
    @GetMapping("/{id}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    // 4. 일정 수정
    @PutMapping("/{id}")
    public String updateSchedule(@PathVariable Long id,
                                 @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.updateSchedule(id, requestDto, requestDto.getPassword());
        return "일정이 성공적으로 수정되었습니다.";
    }

    // 5. 일정 삭제
    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id,
                                 @RequestBody SchedulePasswordDto passwordDto) {
        scheduleService.deleteSchedule(id, passwordDto.getPassword());
        return "일정이 성공적으로 삭제되었습니다.";
    }
}
