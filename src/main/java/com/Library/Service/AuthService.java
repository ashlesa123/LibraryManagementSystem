package com.Library.Service;

import com.Library.DTO.*;
import com.Library.Entity.Book;
import com.Library.Entity.Borrow;
import com.Library.Entity.User;
import com.Library.Repository.BookRepository;
import com.Library.Repository.BorrowRepository;
import com.Library.Repository.UserRepository;
import com.Library.Security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private BorrowRepository borrowRepository;
    private EmailService emailService;

    private JwtService jwtService;

    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, BookRepository bookRepository, BorrowRepository borrowRepository, EmailService emailService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserDTO dto){
        User user =new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        User saved = userRepository.save(user);

        emailService.sendEmail(saved.getEmail(),"Welcome to Library","Hi" + saved.getUsername() + ",\n\nYou've successfully registered.");
        return saved;

    }




    public Book addBook(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setStock(dto.getStock());
        return bookRepository.save(book);
    }

    public Borrow borrowBook(BorrowDTO dto){

        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow();

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(dto.getBorrowDate());
        borrow.setReturnDate(dto.getReturnDate());

        // Reduce book stock
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public String login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            // ✅ Generate token with username and role
            return jwtService.generateToken(user.getUsername(), user.getRole());
        } else {
            throw new RuntimeException("Invalid credentials");
        }

    }
}
