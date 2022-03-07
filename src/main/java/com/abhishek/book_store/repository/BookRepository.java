package com.abhishek.book_store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.book_store.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

}
