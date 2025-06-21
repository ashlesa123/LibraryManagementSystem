package com.Library.Repository;

import com.Library.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Search & filter
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
    Page<Book> findByStockGreaterThan(int stock, Pageable pageable);
    Page<Book> findByCategoryAndStockGreaterThan(String category, int stock, Pageable pageable);
}