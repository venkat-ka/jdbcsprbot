package com.jdbcsprcon.dbjdbc.repo.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbcsprcon.dbjdbc.model.Book;
import com.jdbcsprcon.dbjdbc.repo.BookRepo;

@Repository
public class JdbcbookRepoImpl implements BookRepo {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		// TODO Auto-generated method stub
		 return jdbcTemplate
	                .queryForObject("select count(*) from books", Integer.class);
	}

	@Override
	public int save(Book book) {
		// TODO Auto-generated method stub
		  return jdbcTemplate.update(
	                "insert into books (name, price) values(?,?)",
	                book.getName(), book.getPrice());
	}

	@Override
	public int update(Book book) {
		
		 return jdbcTemplate.update(
	                "update books set price = ? where id = ?",
	                book.getPrice(), book.getId());
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.update(
	                "delete books where id = ?",
	                id);
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(
                "select * from books",
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
	}

	@Override
	public List<Book> findByNameAndPrice(String name, BigDecimal price) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(
                "select * from books where name like ? and price <= ?",
                new Object[]{"%" + name + "%", price},
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
	}

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.queryForObject(
	                "select * from books where id = ?",
	                new Object[]{id},
	                (rs, rowNum) ->
	                        Optional.of(new Book(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getBigDecimal("price")
	                        ))
	        );
	}

	@Override
	public String getNameById(Long id) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.queryForObject(
	                "select name from books where id = ?",
	                new Object[]{id},
	                String.class
	        );
	}

}
