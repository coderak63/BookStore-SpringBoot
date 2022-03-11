package com.abhishek.book_store.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.book_store.model.Book;
import com.abhishek.book_store.model.Rating;
import com.abhishek.book_store.service.RatingService;

@RestController
@RequestMapping("/api")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	
	@GetMapping("ratings/average/{book_id}")
	public ResponseEntity<?> getratingsByBookId(@PathVariable String book_id)
	{
		return ratingService.getAvgRatingsByBookId(book_id);
	}
	
	@GetMapping("ratings/{book_id}")
	public ResponseEntity<?> getAllRatingsByBookId(@PathVariable String book_id)
	{
		return ratingService.getAllRatingsByBookId(book_id);
	}
	
	@PostMapping("ratings")
	public ResponseEntity<?> postRatingsByUsernameAndBookId(@Valid @RequestBody Rating rating)
	{
		return ratingService.postRatingsByUsernameAndBookId(rating);
	}
	
	
	@GetMapping("ratings/username/{username}/{book_id}")
	public ResponseEntity<?> getRatingsByUsernameAndBookId(@PathVariable String username,@PathVariable String book_id)
	{
		return ratingService.getRatingsByUsernameAndBookId(username,book_id);
	}
	
}
