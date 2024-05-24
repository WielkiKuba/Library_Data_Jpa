package com.Library.Library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findById(long id);
    List<Rent> findByClient(long client);
    Optional<Rent> findByClientAndBook(long client,long book);
    List<Rent> findBybook(long book);
}
