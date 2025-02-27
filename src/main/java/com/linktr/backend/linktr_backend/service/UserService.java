package com.linktr.backend.linktr_backend.service;

import com.linktr.backend.linktr_backend.dtos.ChangePassword;
import com.linktr.backend.linktr_backend.dtos.UpdateProfileDto;
import com.linktr.backend.linktr_backend.dtos.UserResponse;
import com.linktr.backend.linktr_backend.models.CustomLink;
import com.linktr.backend.linktr_backend.models.SocialLink;

public interface UserService {

    UserResponse updateProfile(Long userId, UpdateProfileDto updateProfileDto);

    UserResponse updateSocialLink(Long userId, SocialLink socialLink);

    CustomLink addCustomLink(Long userId, CustomLink customLink);

    String deleteCustomLink(Long userId, Long customLinkId);

    UserResponse findById(Long userId);

    UserResponse findByUsername(String username);

    void changePassword(ChangePassword changePasswordDto);

}
