package com.linktr.backend.linktr_backend.dtos;

import com.linktr.backend.linktr_backend.models.CustomLink;
import com.linktr.backend.linktr_backend.models.SocialLink;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String urlUsername;
    private String email;
    private boolean isEnabled;
    private Date createdDate;
    private String profileTitle;
    private String profileBio;
    private SocialLink socialLink;
    private List<CustomLink> customLinks;
}

