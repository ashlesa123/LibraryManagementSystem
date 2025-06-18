package com.Library.Controller;

import com.Library.DTO.BookDTO;
import com.Library.DTO.BorrowDTO;
import com.Library.DTO.UserDTO;
import com.Library.Entity.Book;
import com.Library.Entity.Borrow;
import com.Library.Entity.User;
import com.Library.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/addUser")
    public User addUser(@RequestBody @Valid UserDTO userDTO) {
        return authService.addUser(userDTO);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody @Valid BookDTO bookDTO) {
        return authService.addBook(bookDTO);
    }

    @PostMapping("/borrowBook")
    public Borrow borrowBook(@RequestBody @Valid BorrowDTO borrowDTO) {
        return authService.borrowBook(borrowDTO);
    }
}

