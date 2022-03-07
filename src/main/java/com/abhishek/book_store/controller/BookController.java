package com.abhishek.book_store.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.book_store.model.Book;
import com.abhishek.book_store.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	

	@Autowired
	private BookService bookService;
	
	@GetMapping("books")
	public ResponseEntity<List<Book>> getBooks()
	{
		return bookService.getBooks();
	}
	
	@GetMapping("books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable String id)
	{
		return bookService.getBook(id);
	}
	
	
	@PostMapping("add-book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book)
	{
		book.setDate_created(new Date());
		
		return bookService.addBook(book);
	}
	
	@PutMapping("edit-book/{id}")
	public ResponseEntity<Book> editBook(@PathVariable(name = "id") String id, @RequestBody Book book)
	{		
		return bookService.editBook(id,book);
	}
	
	@DeleteMapping("delete-book/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable String id)
	{	
		return bookService.deleteBook(id);
	}
	
	@DeleteMapping("delete-all-books")
	public ResponseEntity<HttpStatus> deleteAllBooks()
	{	
		return bookService.deleteAllBooks();
	}
	

	@PatchMapping("edit-book/{id}")
	public ResponseEntity<Book> editBookPatch(@PathVariable(name = "id") String id, @RequestBody Map<String, Object> fields)
	{		
		return bookService.editBookPatch(id,fields);
	}
	
	
	
}
