package com.azl6.mongodb.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookRequest {

    @NotNull(message = "O campo title não pode ser nulo.")
    @NotBlank(message = "O campo title não pode estar em branco.")
    private String title;

    @NotNull(message = "O campo rating não pode ser nulo.")
    @Min(value = 0, message = "O campo rating não pode ser negativo")
    private Integer rating;

}
