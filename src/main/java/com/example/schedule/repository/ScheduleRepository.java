package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Schedule schedule) {
        String sql = "INSERT INTO schedules (title, author, password, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())";
        return jdbcTemplate.update(sql,
                schedule.getTitle(),
                schedule.getAuthor(),
                schedule.getPassword()
        );
    }
}
