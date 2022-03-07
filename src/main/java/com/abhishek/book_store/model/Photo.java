package com.abhishek.book_store.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo {
    @Id
    private String id;
    
    private String title;
    
    private String book_id;
        
    private Binary image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}
	

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public Photo() {
		super();
	}

	public Photo(String id, String title, String book_id, Binary image) {
		super();
		this.id = id;
		this.title = title;
		this.book_id = book_id;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", title=" + title + ", book_id=" + book_id + ", image=" + image + "]";
	}

	
    
    
}
