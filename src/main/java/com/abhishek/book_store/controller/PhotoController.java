package com.abhishek.book_store.controller;




import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abhishek.book_store.model.Book;
import com.abhishek.book_store.model.Photo;
import com.abhishek.book_store.service.PhotoService;

@RestController
@RequestMapping("/api")
public class PhotoController {
	
	@Autowired
	PhotoService photoService;
	
	@GetMapping("/photos/{id}")
	public ResponseEntity<Photo> getPhotoById(@PathVariable String id) {
		return photoService.getPhotoById(id);
		    /*
		    String imageInString = 
		      Base64.getEncoder().encodeToString(photo.getImage().getData());
		      */
	}
	
	@GetMapping("/photos/by-book-id/{book_id}")
	public ResponseEntity<Photo> getPhotoByBookId(@PathVariable String book_id) {

	    return photoService.getPhotoByBookId(book_id);
		    /*
		    String imageInString = 
		      Base64.getEncoder().encodeToString(photo.getImage().getData());
		      */
	}
	
	@PostMapping("upload-photo")
	public ResponseEntity<Photo> handleFileUpload(@RequestParam("image") MultipartFile file,@RequestParam("book_id") String book_id,@RequestParam("title") String title) {

	     try {
			return photoService.uploadFile(file,book_id,title);
		} catch (IOException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	   }
	
	@PutMapping("update-photo")
	public ResponseEntity<Photo> handleFileUpdate(@RequestParam("image") MultipartFile file,@RequestParam("book_id") String book_id,@RequestParam("title") String title) {

	     return photoService.updateFile(file,book_id,title);

	   }

}
