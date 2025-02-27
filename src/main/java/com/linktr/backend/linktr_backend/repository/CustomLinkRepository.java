package com.linktr.backend.linktr_backend.repository;

import com.linktr.backend.linktr_backend.models.CustomLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomLinkRepository extends JpaRepository<CustomLink, Long> {
}
