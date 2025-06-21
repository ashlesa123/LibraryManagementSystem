package com.Library.DTO;

import java.time.LocalDate;

public class BorrowResponseDTO {

    private Long id;
    private String username;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowResponseDTO() {
    }

    public BorrowResponseDTO(Long id, String username, String bookTitle, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.username = username;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
