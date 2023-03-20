package com.azl6.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "books")
@Data
public class Book {

    @Id
    private String id;
    private String title;
    private Integer rating;
}
