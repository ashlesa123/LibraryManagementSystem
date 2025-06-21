package com.Library.Service;

import com.Library.DTO.BookDTO;
import com.Library.Entity.Book;
import com.Library.Repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(BookDTO dto) {
        Book book = new Book(dto.getTitle(), dto.getAuthor(), dto.getCategory(), dto.getStock());
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }


    public Page<Book> getAllBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(Long id, BookDTO dto) {
        Book book = getBookById(id);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setStock(dto.getStock());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }


    public Page<Book> searchBooks(String title, String author, String category, Boolean available, Pageable pageable) {
        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else if (author != null) {
            return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
        } else if (category != null && available != null) {
            return bookRepository.findByCategoryAndStockGreaterThan(category, 0, pageable);
        } else if (category != null) {
            return bookRepository.findByCategoryContainingIgnoreCase(category, pageable);
        } else if (available != null && available) {
            return bookRepository.findByStockGreaterThan(0, pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }

}
