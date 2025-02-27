package com.linktr.backend.linktr_backend.repository;

import com.linktr.backend.linktr_backend.models.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {

    Optional<EmailConfirmation> findByToken(String token);
}

