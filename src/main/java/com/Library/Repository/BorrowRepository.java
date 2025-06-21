package com.Library.Repository;

import com.Library.Entity.Book;
import com.Library.Entity.Borrow;
import com.Library.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    boolean existsByUserAndBookAndReturnDateIsNull(User user, Book book);
    List<Borrow> findByUser(User user);
}