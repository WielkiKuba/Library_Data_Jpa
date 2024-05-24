package com.Library.Library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findByFree(int free);
    Optional<Book> findById(long id);
}
