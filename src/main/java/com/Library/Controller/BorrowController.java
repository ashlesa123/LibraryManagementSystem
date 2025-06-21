package com.Library.Controller;

import com.Library.DTO.BorrowDTO;
import com.Library.DTO.BorrowResponseDTO;
import com.Library.Entity.Borrow;
import com.Library.Service.AuthService;
import com.Library.Service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/borrow")
public class BorrowController {

    private final BorrowService borrowService;
    private final AuthService authService;

    public BorrowController(BorrowService borrowService, AuthService authService) {
        this.borrowService = borrowService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<BorrowResponseDTO> borrowBook(@RequestBody @Valid BorrowDTO dto) {
        return ResponseEntity.ok(borrowService.borrowBook(dto));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<BorrowResponseDTO> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<BorrowResponseDTO>> getUserHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowService.getUserBorrowHistory(userId));
}


}
