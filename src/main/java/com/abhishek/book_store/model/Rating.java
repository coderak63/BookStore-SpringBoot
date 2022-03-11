package com.abhishek.book_store.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
public class Rating {
	
	@Id
    private String id;
    
	@NotBlank(message = "Username should not be blank!")
    private String username;
    
	@NotBlank(message = "Book Id should not be blank!")
    private String book_id;
    
	@NotNull(message = "Rating should not be null!")
	@Min(value = 1, message = "Rating should be greater than zero.")
	@Max(value = 5,message = "Rating should be less than six.")
    private int rating;
	
	private String title,review;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Rating(String id, @NotBlank(message = "Username should not be blank!") String username,
			@NotBlank(message = "Book Id should not be blank!") String book_id,
			@NotNull(message = "Rating should not be null!") @Min(value = 1, message = "Rating should be greater than zero.") @Max(value = 5, message = "Rating should be less than six.") int rating,
			String title, String review) {
		super();
		this.id = id;
		this.username = username;
		this.book_id = book_id;
		this.rating = rating;
		this.title = title;
		this.review = review;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", username=" + username + ", book_id=" + book_id + ", rating=" + rating
				+ ", title=" + title + ", review=" + review + "]";
	}

	
    
    
	

}
