package com.abhishek.book_store.service;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhishek.book_store.model.Book;


@Service
public interface BookService {
	
	


	ResponseEntity<List<Book>> getBooks();

	ResponseEntity<Book> getBook(String id);

	ResponseEntity<Book> addBook(Book book);

	ResponseEntity<Book> editBook(String id, Book book);

	ResponseEntity<HttpStatus> deleteBook(String id);

	ResponseEntity<HttpStatus> deleteAllBooks();

	ResponseEntity<Book> editBookPatch(String id, Map<String, Object> fields);



}
