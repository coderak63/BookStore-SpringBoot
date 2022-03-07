package com.abhishek.book_store.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhishek.book_store.controller.BookController;
import com.abhishek.book_store.model.Book;
import com.abhishek.book_store.repository.BookRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;




@Service
public class BookServiceImplementation implements BookService{
	
	Logger logger = LoggerFactory.getLogger(BookController.class);
	
	
	@Autowired
	private BookRepository bookRepository;
	

	@Override
	public ResponseEntity<List<Book>> getBooks() {

		try {
		    List<Book> books = bookRepository.findAll();
		    
		    return new ResponseEntity<>(books, HttpStatus.OK);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@Override
	public ResponseEntity<Book> getBook(String id) {
		
		try {
			Optional<Book> book = bookRepository.findById(id);
		    
		    if (book.isPresent()) {
		      return new ResponseEntity<>(book.get(),HttpStatus.OK);
		    }
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@Override
	public ResponseEntity<Book> addBook(Book book) {
		
		try {		
				
			Book _book = bookRepository.insert(book);
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@Override
	public ResponseEntity<Book> editBook(String id, Book book) {

		try {
			
			Optional<Book> _book = bookRepository.findById(id);
			
			if (_book.isPresent()) {	
				
				  book.setId(id);
			      return new ResponseEntity<>(bookRepository.save(book),HttpStatus.OK);
			    }
			else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@Override
	public ResponseEntity<HttpStatus> deleteBook(String id) {
		
		try {
			
		    Optional<Book> book = bookRepository.findById(id);
		    
		    if (book.isPresent()) {
		    	bookRepository.deleteById(id);
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }else {
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@Override
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		
		try {
		    bookRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@Override
	public ResponseEntity<Book> editBookPatch(String id, Map<String, Object> fields) {
		
		try {
			Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException());
	
				try {
					for(Map.Entry<String,Object> field : fields.entrySet()){
						if(field.getKey().equals("id"))
							continue;
						
						if(field.getValue().equals(""))
							return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
						
						if(field.getKey().equals("authors")) {
							logger.info(field.getValue().toString());
							List<String> authors = (List<String>) field.getValue();
							if(authors.size()==0)
								return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
								
							book.setField(field.getKey(), field.getValue());
						}else if(field.getKey().equals("price")) {
							float value = Float.parseFloat(field.getValue().toString());
							book.setField(field.getKey(), value); 
						}else {
							book.setField(field.getKey(), field.getValue()); 
						}
						
						  
				    }
				}catch (NoSuchFieldException e) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			    } catch (IllegalAccessException e) {
			    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			    }
			
				  
				  //book.setId(id);
			return new ResponseEntity<>(bookRepository.save(book),HttpStatus.OK);
			
		  } 
		catch (NotFoundException e) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}
	
	
	

}
