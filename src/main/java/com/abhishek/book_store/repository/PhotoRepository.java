package com.abhishek.book_store.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhishek.book_store.model.Photo;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> 
{
	@Query("{'book_id' : ?0 }")
	Optional<Photo> findByBookId(String book_id); 
	

}
