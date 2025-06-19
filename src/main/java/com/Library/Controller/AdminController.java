package com.Library.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/books")
    public ResponseEntity<String> getBooks() {
        return ResponseEntity.ok("✅ Admin books listed");
    }
}
