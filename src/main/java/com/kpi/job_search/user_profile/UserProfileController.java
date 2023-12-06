package com.kpi.job_search.user_profile;

import java.util.UUID;

import com.kpi.job_search.user_profile.dto.CreateUserProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public UserProfileDto getUserProfile(@PathVariable UUID userId) {
        return userProfileService.getUserProfile(userId);
    }

    @PostMapping
    public UserProfileDto createUserProfile(@RequestBody CreateUserProfileDto userProfileDto) {
        return userProfileService.createUserProfile(userProfileDto);
    }

    @PutMapping
    public UserProfileDto updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
        return userProfileService.updateUserProfile(userProfileDto);
    }

}
