package com.azl6.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azl6.mongodb.exceptions.ObjectNotFoundException;
import com.azl6.mongodb.model.Book;
import com.azl6.mongodb.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> findAllBooks(){

        return bookRepository.findAll();

    }

    public Book insertBook(Book book){
        
        Book savedBook = bookRepository.save(book);

        return savedBook;

    }

    public Book updateBook(String bookId, Book newBook){

        Optional<Book> oldBookOpt = bookRepository.findById(bookId);

        Book oldBook = oldBookOpt.orElseThrow(() -> new ObjectNotFoundException("O objeto não foi encontrado."));

        BeanUtils.copyProperties(newBook, oldBook, "id");

        return bookRepository.save(oldBook);
        
    }

    public void deleteBook(String bookId){

        Optional<Book> toBeDeletedOpt = bookRepository.findById(bookId);

        Book toBeDeleted = toBeDeletedOpt.orElseThrow(() -> new ObjectNotFoundException("O objeto não foi encontrado."));

        bookRepository.delete(toBeDeleted);

    }

}
