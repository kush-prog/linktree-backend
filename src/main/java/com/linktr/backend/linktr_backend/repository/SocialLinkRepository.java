package com.linktr.backend.linktr_backend.repository;

import com.linktr.backend.linktr_backend.models.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLinkRepository extends JpaRepository<SocialLink, Long> {
}

