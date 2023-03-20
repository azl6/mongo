package com.azl6.mongodb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.azl6.mongodb.exceptions.ObjectNotFoundException;
import com.azl6.mongodb.model.Book;
import com.azl6.mongodb.repositories.BookRepository;

@SpringBootTest
public class BookServiceTest {
    
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    List<Book> listaComUmBook = new ArrayList<>();

    Book bookTestes;

    Book novoBook;

    Book bookAtualizado;

    @BeforeEach
    public void setUp() {

        listaComUmBook = List.of(new Book("idtest", "TEST", 10));

        bookTestes = new Book("idtest", "TEST", 10);

        novoBook = new Book(null, "ATUALIZADO", 5);

        bookAtualizado = new Book("idtest", "ATUALIZADO", 5);

        BDDMockito.given(bookRepository.findAll()).willReturn(Collections.emptyList());

    }

    @Test
    void findAllBooks_shouldReturnEmptyList(){

        List<Book> response = bookService.findAllBooks();

        assertEquals(Collections.emptyList(), response);

        BDDMockito.then(bookRepository).should(times(1)).findAll();

    }

    @Test
    void findAllBooks_shouldReturnOneBook(){

        BDDMockito.given(bookRepository.findAll()).willReturn(listaComUmBook);

        List<Book> response = bookService.findAllBooks();

        assertEquals(1, response.size());

        BDDMockito.then(bookRepository).should(times(1)).findAll();

    }

    @Test
    void insertBook_shouldInsertSuccessfully(){

        BDDMockito.given(bookRepository.save(bookTestes)).willReturn(bookTestes);

        Book response = bookService.insertBook(bookTestes);

        assertEquals(bookTestes, response);

        BDDMockito.then(bookRepository).should(times(1)).save(bookTestes);

    }

    @Test
    void updateBook_shouldUpdateSucessfully(){

        BDDMockito.given(bookRepository.findById(bookTestes.getId())).willReturn(Optional.of(bookTestes));
        BDDMockito.given(bookRepository.save(any())).willReturn(bookAtualizado);

        Book response = bookService.updateBook(bookTestes.getId(), novoBook);

        assertEquals("ATUALIZADO", response.getTitle());
        assertEquals(5, response.getRating());
        BDDMockito.then(bookRepository).should(times(1)).save(bookAtualizado);

    }

    @Test
    void updateBook_shouldThrowNotFoundExceptionBecauseTheBookWasNotFound(){

        BDDMockito.given(bookRepository.findById(bookTestes.getId())).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            bookService.updateBook(bookTestes.getId(), novoBook);

        });

    }

    @Test
    void deleteBook_shouldDeleteSucessfully(){

        BDDMockito.given(bookRepository.findById(bookTestes.getId())).willReturn(Optional.of(bookTestes));

        bookService.deleteBook(bookTestes.getId());

        BDDMockito.then(bookRepository).should(times(1)).delete(bookTestes);

    }

    @Test
    void deleteBook_shouldThrowNotFoundExceptionBecauseTheBookWasNotFound(){
        
        BDDMockito.given(bookRepository.findById(bookTestes.getId())).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            bookService.deleteBook(bookTestes.getId());
        });

    }



}
