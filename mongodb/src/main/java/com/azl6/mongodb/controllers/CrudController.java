package com.azl6.mongodb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azl6.mongodb.model.Book;
import com.azl6.mongodb.model.BookRequest;
import com.azl6.mongodb.services.BookService;

@RestController
public class CrudController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/insertBook")
    public ResponseEntity<Book> insertBook(@Valid @RequestBody BookRequest bookRequest){
        
        Book bookEntity = modelMapper.map(bookRequest, Book.class);

        Book savedBook = bookService.insertBook(bookEntity);

        return ResponseEntity.status(HttpStatus.OK).body(savedBook);

    }
    
    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> findAllBooks(){

        List<Book> books = bookService.findAllBooks();

        return ResponseEntity.status(HttpStatus.OK).body(books);

    }

    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<Book> updateBook(
    @PathVariable(required = true) String bookId, 
    @Valid @RequestBody(required = true) BookRequest bookRequest){

        Book newBook = modelMapper.map(bookRequest, Book.class);

        Book updatedBook = bookService.updateBook(bookId, newBook);

        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);

    }

    @DeleteMapping("/deleteBook/{bookId}")
    public void deleteBook(@PathVariable String bookId){

        bookService.deleteBook(bookId);

    }

}