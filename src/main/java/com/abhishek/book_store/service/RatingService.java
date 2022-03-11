package com.abhishek.book_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhishek.book_store.model.Photo;
import com.abhishek.book_store.model.Rating;
import com.abhishek.book_store.model.RatingResponse;
import com.abhishek.book_store.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	public ResponseEntity<?> getAvgRatingsByBookId(String book_id) { 
	       
	        try {
				//float avgRatings = ratingRepository.avgRating(book_id);
				Optional<List<Rating>> ratings = ratingRepository.findByBookId(book_id);

				if (ratings.isPresent()) {
			    	int n=ratings.get().size();
			    	
			    	if(n==0) {
			    		RatingResponse rs = new RatingResponse(0,0,0,0,0,0);
			    		return new ResponseEntity<>(rs,HttpStatus.OK);
			    	}
					
			    	
			    	float sum=0;
			    	int rat[] = new int[5];
			    	for(Rating r:ratings.get()) {
			    		sum += r.getRating(); 
			    		rat[r.getRating()-1]++;
			    	}
			    	
			    	float avg=sum/n;
			    	RatingResponse rs = new RatingResponse(avg, rat[0], rat[1], rat[2], rat[3], rat[4]);
			    	return new ResponseEntity<>(rs,HttpStatus.OK);
			    	
			    }
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  
			  } 
			catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	    }

	public ResponseEntity<?> postRatingsByUsernameAndBookId(Rating rating) {
		
		try {
			Optional<Rating> _rating = ratingRepository.findByUserNameAndBookId(rating.getUsername(), rating.getBook_id());
		    
		    if (_rating.isPresent()) {
		    	//Rating rating = new Rating(_rating.get().getId(), username, book_id, rating.getRating());
		    	rating.setId(_rating.get().getId());
				return new ResponseEntity<>(ratingRepository.save(rating),HttpStatus.OK);
		    	
		    }
		    return new ResponseEntity<>(ratingRepository.insert(rating),HttpStatus.OK);
		       
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	public ResponseEntity<?> getRatingsByUsernameAndBookId(String username, String book_id) {
		
		try {
			Optional<Rating> rating = ratingRepository.findByUserNameAndBookId(username, book_id);
		    
		    if (rating.isPresent()) {
		      return new ResponseEntity<>(rating.get(),HttpStatus.OK);
		    }
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	public ResponseEntity<?> getAllRatingsByBookId(String book_id) {
		try {
			//float avgRatings = ratingRepository.avgRating(book_id);
			Optional<List<Rating>> ratings = ratingRepository.findByBookId(book_id);
			return new ResponseEntity<>(ratings.get(),HttpStatus.OK);
			/*
			if (ratings.isPresent()) {
				
		    	return new ResponseEntity<>(ratings.get(),HttpStatus.OK);
		    	
		    }
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	*/
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
}
