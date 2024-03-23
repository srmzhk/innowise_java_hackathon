package org.srmzhk.crypto_bot.crypto_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllData() {
        String sql = "SELECT * FROM Data";
        return jdbcTemplate.queryForList(sql);
    }
}