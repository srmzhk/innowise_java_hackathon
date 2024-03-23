package org.srmzhk.crypto_bot.crypto_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.srmzhk.crypto_bot.crypto_bot.model.Data;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@SpringBootApplication
public class CryptoBotApplication{

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	@Autowired
//	private DataSource dataSource;


	public static void main(String[] args) {
		SpringApplication.run(CryptoBotApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception{
//		System.out.println(dataSource.toString());
//		try (Connection connection = dataSource.getConnection()) {
//			System.out.println("Connected to database: " + connection.getMetaData().getDatabaseProductName());
//		} catch (Exception e) {
//			System.err.println("Failed to connect to database: " + e.getMessage());
//		}
//		String sql = "SELECT * FROM Data";
//		List<Data> data = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Data.class));
//		data.forEach(System.out :: println);
}
