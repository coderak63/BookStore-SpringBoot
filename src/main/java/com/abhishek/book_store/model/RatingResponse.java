package com.abhishek.book_store.model;

public class RatingResponse {
	
	float rating_avg;
	int rating_1,rating_2,rating_3,rating_4,rating_5;
	
	public RatingResponse(float rating_avg, int rating_1, int rating_2, int rating_3, int rating_4, int rating_5) {
		this.rating_avg = rating_avg;
		this.rating_1 = rating_1;
		this.rating_2 = rating_2;
		this.rating_3 = rating_3;
		this.rating_4 = rating_4;
		this.rating_5 = rating_5;
	}

	public float getRating_avg() {
		return rating_avg;
	}

	public void setRating_avg(float rating_avg) {
		this.rating_avg = rating_avg;
	}

	public int getRating_1() {
		return rating_1;
	}

	public void setRating_1(int rating_1) {
		this.rating_1 = rating_1;
	}

	public int getRating_2() {
		return rating_2;
	}

	public void setRating_2(int rating_2) {
		this.rating_2 = rating_2;
	}

	public int getRating_3() {
		return rating_3;
	}

	public void setRating_3(int rating_3) {
		this.rating_3 = rating_3;
	}

	public int getRating_4() {
		return rating_4;
	}

	public void setRating_4(int rating_4) {
		this.rating_4 = rating_4;
	}

	public int getRating_5() {
		return rating_5;
	}

	public void setRating_5(int rating_5) {
		this.rating_5 = rating_5;
	}
	
	
	

}
