package com.Library.Library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameAndSurname(String name,String surname);
    List<Client> findById(long id);
}
