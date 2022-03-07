package com.abhishek.book_store.service;

import java.io.IOException;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abhishek.book_store.model.Book;
import com.abhishek.book_store.model.Photo;
import com.abhishek.book_store.repository.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    

    public ResponseEntity<Photo> getPhotoById(String id) { 
       // return photoRepo.findById(id).get(); 
        try {
			Optional<Photo> photo = photoRepo.findById(id);
		    
		    if (photo.isPresent()) {
		      return new ResponseEntity<>(photo.get(),HttpStatus.OK);
		    }
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
    }

	public ResponseEntity<Photo> getPhotoByBookId(String book_id) {
		//return photoRepo.findByBookId(book_id).get();
		try {
			Optional<Photo> photo = photoRepo.findByBookId(book_id);
		    
		    if (photo.isPresent()) {
		      return new ResponseEntity<>(photo.get(),HttpStatus.OK);
		    }
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	public ResponseEntity<Photo> uploadFile(MultipartFile file, String book_id, String title) throws IOException {
        
        try {		
        	Photo photo = new Photo();
            photo.setTitle(title);
            photo.setBook_id(book_id);
            photo.setImage(
                    new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
            Photo _photo = photoRepo.insert(photo);
			return new ResponseEntity<>(_photo, HttpStatus.CREATED);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	public ResponseEntity<Photo> updateFile(MultipartFile file, String book_id, String title) {
		try {	
			Optional<Photo> _photo = photoRepo.findByBookId(book_id);
			Photo photo = new Photo();
			
			if(_photo.isPresent()) {
			      photo.setId(_photo.get().getId());
			 }
			
            photo.setTitle(title);
            photo.setBook_id(book_id);
            photo.setImage(
                    new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
            Photo __photo = photoRepo.save(photo);
			return new ResponseEntity<>(__photo, HttpStatus.CREATED);
		  } 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
}
