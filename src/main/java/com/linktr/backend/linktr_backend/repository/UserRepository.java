package com.linktr.backend.linktr_backend.repository;

import com.linktr.backend.linktr_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUrlUsername(String username);
    Optional<User> findByEmail (String email);
}

