package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookRepository;
import com.example.demo.model.Book;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> listAll() {
		return bookRepository.findAll();
	}
	
	public void save(Book book) {
		bookRepository.save(book);
	}
	
	public Book get(long id) {
		return bookRepository.findById(id).get();
	}
	
	public void delete(long id) {
		bookRepository.deleteById(id);
	}
	


}
