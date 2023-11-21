package com.kpi.job_search.user_profile;

import java.util.UUID;

import com.kpi.job_search.user_profile.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfileDto getUserProfile(@PathVariable UUID id) {
        return userProfileService.getUserProfile(id);
    }

    @PostMapping
    public UserProfileDto saveUserProfile(@RequestBody UserProfileDto userProfileDto) {
        return userProfileService.saveUserProfile(userProfileDto);
    }

}
