package com.abhishek.book_store.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhishek.book_store.model.Rating;



@Repository
public interface RatingRepository extends MongoRepository<Rating, String> 
{
	@Query("{'book_id' : ?0 }")
	Optional<List<Rating>> findByBookId(String book_id); 
	
	@Query("{'username' : ?0 ,'book_id' : ?1 }")
	Optional<Rating> findByUserNameAndBookId(String username,String book_id); 
	

	

}

