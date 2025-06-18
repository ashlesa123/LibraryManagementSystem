package com.Library.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class BookDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String category;

    @PositiveOrZero
    private int stock;

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public @NotBlank String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank String author) {
        this.author = author;
    }

    public @NotBlank String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank String category) {
        this.category = category;
    }

    public @PositiveOrZero int getStock() {
        return stock;
    }

    public void setStock(@PositiveOrZero int stock) {
        this.stock = stock;
    }
}
