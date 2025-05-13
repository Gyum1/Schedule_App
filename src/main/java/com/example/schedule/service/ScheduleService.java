package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다."));
        return convertToResponseDto(schedule);
    }

    // ✅ 추가: 일정 수정 (비밀번호 검증)
    public void updateSchedule(Long id, ScheduleRequestDto requestDto, String password) {
        boolean valid = scheduleRepository.checkPassword(id, password);
        if (!valid) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        scheduleRepository.update(id, requestDto.getTitle(), requestDto.getAuthor());
    }

    // ✅ 추가: 일정 삭제 (비밀번호 검증)
    public void deleteSchedule(Long id, String password) {
        boolean valid = scheduleRepository.checkPassword(id, password);
        if (!valid) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        scheduleRepository.delete(id);
    }

    private ScheduleResponseDto convertToResponseDto(Schedule schedule) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setAuthor(schedule.getAuthor());
        dto.setCreatedAt(schedule.getCreatedAt());
        dto.setUpdatedAt(schedule.getUpdatedAt());
        return dto;
    }
}
