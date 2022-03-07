package com.abhishek.book_store.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Document(collection = "books")
public class Book {
	@Id
	private String id;
	
	@NotBlank(message = "Please add title!")
	private String title;
	
	@NotBlank(message = "Please add description!")
	private String description;
	
	@NotBlank(message = "Please add isbn!")
	private String isbn;
	
	@NotEmpty(message = "Please add authors!")
	private List<String> authors;
	
	@NotBlank(message = "Please add publisher!")
	private String publisher;
	
	@NotBlank(message = "Please add language!")
	private String language;
	
	@NotBlank(message = "Please add category!")
	private String category;
	
	@NotNull(message = "Please add price!")
	@Min(value = 1, message = "Price should be positive value.")
    private float price;
	
	@NotNull(message = "Please add page count!")
	@Min(value = 1, message = "Page count should be greater than zero.")
    private int page_count;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date_created;
	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPage_count() {
		return page_count;
	}

	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

	public Book() {
		super();
	}

	

	public Book(String id, @NotBlank(message = "Please add title!") String title,
			@NotBlank(message = "Please add description!") String description,
			@NotBlank(message = "Please add isbn!") String isbn,
			@NotEmpty(message = "Please add authors!") List<String> authors,
			@NotBlank(message = "Please add publisher!") String publisher,
			@NotBlank(message = "Please add language!") String language,
			@NotBlank(message = "Please add category!") String category,
			@NotNull(message = "Please add price!") @Min(value = 1, message = "Price should be positive value.") float price,
			@NotNull(message = "Please add page count!") @Min(value = 1, message = "Page count should be greater than zero.") int page_count,
			Date date_created) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.authors = authors;
		this.publisher = publisher;
		this.language = language;
		this.category = category;
		this.price = price;
		this.page_count = page_count;
		this.date_created = date_created;
	}

	
	

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", isbn=" + isbn + ", authors="
				+ authors + ", publisher=" + publisher + ", language=" + language + ", category=" + category
				+ ", price=" + price + ", page_count=" + page_count + ", date_created=" + date_created + "]";
	}

	public void setField(String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        	Field field = getClass().getDeclaredField(name);
            field.set(this,value);	
    }
	
	
	

}
