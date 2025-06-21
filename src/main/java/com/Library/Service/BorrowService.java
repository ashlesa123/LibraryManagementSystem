package com.Library.Service;

import com.Library.DTO.BorrowDTO;
import com.Library.DTO.BorrowResponseDTO;
import com.Library.Entity.Book;
import com.Library.Entity.Borrow;
import com.Library.Entity.User;
import com.Library.Repository.BookRepository;
import com.Library.Repository.BorrowRepository;
import com.Library.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;

    public BorrowService(UserRepository userRepository, BookRepository bookRepository, BorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    public BorrowResponseDTO borrowBook(BorrowDTO dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() <= 0) {
            throw new RuntimeException("Book out of stock");
        }

        boolean alreadyBorrowed = borrowRepository.existsByUserAndBookAndReturnDateIsNull(user, book);
        if (alreadyBorrowed) {
            throw new RuntimeException("User already borrowed this book");
        }

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(dto.getBorrowDate());
        borrow.setDueDate(dto.getDueDate());
        borrow.setReturnDate(null);

        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        Borrow saved = borrowRepository.save(borrow);
        return mapToDTO(saved);
    }
    public BorrowResponseDTO returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (borrow.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        borrow.setReturnDate(LocalDate.now());

        Book book = borrow.getBook();
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        Borrow updated = borrowRepository.save(borrow);
        return mapToDTO(updated);
    }

    public List<BorrowResponseDTO> getUserBorrowHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Borrow> borrows = borrowRepository.findByUser(user);
        return borrows.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    private BorrowResponseDTO mapToDTO(Borrow borrow) {
        return new BorrowResponseDTO(
                borrow.getId(),
                borrow.getUser().getUsername(),
                borrow.getBook().getTitle(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                borrow.getDueDate()
        );
    }
}
