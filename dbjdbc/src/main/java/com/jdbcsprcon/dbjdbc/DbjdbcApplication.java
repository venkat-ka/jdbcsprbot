package com.jdbcsprcon.dbjdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jdbcsprcon.dbjdbc.model.Book;
import com.jdbcsprcon.dbjdbc.repo.BookRepo;

@SpringBootApplication
public class DbjdbcApplication implements CommandLineRunner {
	
	@Autowired
	JdbcTemplate jdbcTem;
	
	@Autowired
	@Qualifier("namedParameterJdbcBookRepository") 
	public BookRepo bookRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(DbjdbcApplication.class, args);
	
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		jdbcTem.execute("DROP TABLE IF EXISTS books");
		jdbcTem.execute("CREATE TABLE books(" +
                "id SERIAL, name VARCHAR(255), price NUMERIC(15, 2))");
	}
}
