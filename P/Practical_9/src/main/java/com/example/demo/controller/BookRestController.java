package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Book;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
	
	//In-memory Storage
	private List<Book> bookList = new ArrayList<>();
	
	//Add book
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
		bookList.add(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}
	
	//Get All Books
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks()
	{
		return ResponseEntity.ok(bookList);
	}
	
	//Get Book by Id
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id)
	{
		for(Book book : bookList)
		{
			if(book.getId() == id)
			{
				return ResponseEntity.ok(book);
			}
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id,@RequestBody Book bookDetails)
	{
		for(Book book : bookList)
		{
			if(book.getId() == id)
			{
				book.setTitle(bookDetails.getTitle());
				book.setAuthor(bookDetails.getAuthor());
				book.setPrice(bookDetails.getPrice());
				return ResponseEntity.ok(book);
			}
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id)
	{
		for(Book book : bookList)
		{
			if(book.getId() == id)
			{
				bookList.remove(book);
				return ResponseEntity.ok("Book Deleted Successfully");
			}
		}
		return ResponseEntity.noContent().build();
	}

}
