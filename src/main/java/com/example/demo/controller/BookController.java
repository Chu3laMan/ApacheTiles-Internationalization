package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;



@Controller
@RequestMapping("/")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	//listAllBooks() method exhibits books that have been registered in the database.
	@GetMapping(value = "/books")
	public String listAllBooks(Model model) {
		model.addAttribute("books", bookService.listAll());
		return "books";
	}
	
	
	//addBookToReadingList method adds a new book to the database, in addition, prevents fields which not allowed during submission
	@PostMapping(value = "/books/add")
	public String addBookToReadingList(@ModelAttribute("newBook") Book book, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors())
			return "newBook";
		bookService.save(book);
		String[] supressedFields = result.getSuppressedFields();
		if(supressedFields.length > 0) 
			throw new RuntimeException("Attempting to bind disallowed fileds " + StringUtils.arrayToCommaDelimitedString(supressedFields));
		return "redirect:/books";
	}
	
	@GetMapping(value = "/books/add")
	public String getAddNewForm(Model model) {
		model.addAttribute("newBook", new Book());
		return "addBook";
	}
	
	
	//intializeBinder() method set up the fields which allowed for binding during submission
	@InitBinder("newBook")
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookName", 
				"author",
				"isbn",
				"language");
		}

}
