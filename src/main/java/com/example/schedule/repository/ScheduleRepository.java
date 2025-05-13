package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    public List<Schedule> findAll() {
        String sql = "SELECT * FROM schedules ORDER BY updated_at DESC";
        return jdbcTemplate.query(sql, new ScheduleRowMapper());
    }

    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedules WHERE id = ?";
        List<Schedule> result = jdbcTemplate.query(sql, new ScheduleRowMapper(), id);
        return result.stream().findFirst();
    }

    private static class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setTitle(rs.getString("title"));
            schedule.setAuthor(rs.getString("author"));
            schedule.setPassword(rs.getString("password"));
            schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return schedule;
        }
    }
}
