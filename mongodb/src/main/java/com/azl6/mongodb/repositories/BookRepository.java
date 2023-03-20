package com.azl6.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.azl6.mongodb.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
