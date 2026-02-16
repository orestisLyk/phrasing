package com.example.phrasing.repository;

import com.example.phrasing.model.Phrase;
import com.example.phrasing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhrase(Phrase phrase);
}
