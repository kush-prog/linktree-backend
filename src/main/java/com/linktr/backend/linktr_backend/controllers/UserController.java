package com.linktr.backend.linktr_backend.controllers;


import com.linktr.backend.linktr_backend.dtos.ChangePassword;
import com.linktr.backend.linktr_backend.dtos.UpdateProfileDto;
import com.linktr.backend.linktr_backend.dtos.UserResponse;
import com.linktr.backend.linktr_backend.models.CustomLink;
import com.linktr.backend.linktr_backend.models.SocialLink;
import com.linktr.backend.linktr_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long userId){
        var user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateProfile/{userId}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable Long userId, @RequestBody UpdateProfileDto updateProfileDto){

        var user = userService.updateProfile(userId, updateProfileDto);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateSocial/{userId}")
    public ResponseEntity<UserResponse> updateSocial(
            @PathVariable Long userId, @RequestBody SocialLink socialLink){

        var user = userService.updateSocialLink(userId, socialLink);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addCustomLink/{userId}")
    public ResponseEntity<CustomLink> addCustomLink(
            @PathVariable Long userId, @RequestBody CustomLink customLink){

        CustomLink link = userService.addCustomLink(userId, customLink);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePasswordResponseResponse(@RequestBody ChangePassword changePasswordDto){
        userService.changePassword(changePasswordDto);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @DeleteMapping("{userId}/deleteCustomLink/{customLinkId}")
    public ResponseEntity<String> deleteCustomLink(
            @PathVariable Long userId, @PathVariable Long customLinkId){

        String link = userService.deleteCustomLink(userId, customLinkId);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }
}

