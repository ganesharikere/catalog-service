package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book
(
    @NotBlank(message = "The book ISBN should not be blank.")
    @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid")
    String isbn,

    @NotBlank(message = "The book title should not be blank.")
    String title,

    @NotBlank(message = "The book author should not be blank.")
    String author,

    @NotNull(message = "The book price must not be empty.")
    @Positive(message = "The book price must be positive.")
    Double price

){}
