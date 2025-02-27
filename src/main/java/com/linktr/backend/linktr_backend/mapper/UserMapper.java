package com.linktr.backend.linktr_backend.mapper;

import com.linktr.backend.linktr_backend.dtos.UserResponse;
import com.linktr.backend.linktr_backend.models.User;

public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .urlUsername(user.getUrlUsername())
                .email(user.getEmail())
                .isEnabled(user.isEnabled())
                .createdDate(user.getCreatedDate())
                .profileTitle(user.getProfileTitle())
                .profileBio(user.getProfileBio())
                .socialLink(user.getSocialLink())
                .customLinks(user.getCustomLinks())
                .build();
    }
}
