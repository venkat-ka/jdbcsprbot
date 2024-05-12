package com.jdbcsprcon.dbjdbc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbcsprcon.dbjdbc.model.Book;
import com.jdbcsprcon.dbjdbc.repo.BookRepo;

@RestController
public class MainController {
	
	@Autowired
	JdbcTemplate jdbcTem;
	
	@Autowired
	@Qualifier("namedParameterJdbcBookRepository") 
	public BookRepo bookRepo;
	
	@GetMapping("/add")
	public ResponseEntity<List<Book>> addEntry(){
		List<Book> bookenty = new ArrayList<>();
		bookenty.add(new Book("firstjdbc", new BigDecimal("939.99")));
		bookenty.add(new Book("secjdbc", new BigDecimal("33")));
		bookenty.add(new Book("thirdjdbc", new BigDecimal("44")));
		bookenty.add(new Book("fourthjdbc", new BigDecimal("55")));
		bookenty.forEach(book -> {
            System.out.println("Saving...{}"+ book.getName());
            bookRepo.save(book);
        });
		return ResponseEntity.status(HttpStatus.CREATED).body(bookenty);
	}
}
