package com.kpi.job_search.user_profile;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.user_profile.dto.ContactsDto;
import com.kpi.job_search.user_profile.dto.ProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/profile")
    public UserProfileDto createUserProfile(@RequestBody ProfileDto profileDto) {
//        return userProfileService.createUserProfile(profileDto);
        return null;
    }

    @PutMapping("/profile")
    public ProfileDto updateUserProfile(@RequestBody ProfileDto profileDto) {
        return userProfileService.updateUserProfile(profileDto);
    }

    @PutMapping("/contacts")
    public ContactsDto updateContacts(@RequestBody ContactsDto contactsDto) {
        return userProfileService.updateContacts(contactsDto);
    }

    @GetMapping("/candidates")
    public List<UserProfileDto> getCandidates(@RequestParam(defaultValue="0") Integer page,
                                              @RequestParam(defaultValue="10") Integer size) {
        return userProfileService.getCandidates(page, size);
    }

    @GetMapping("/candidates/{userId}")
    public UserProfileDto getCandidate(@PathVariable UUID userId) {
        return userProfileService.getCandidate(userId);
    }

    @GetMapping("/candidates/vacancy/{vacancyId}")
    public List<UserProfileDto> getCandidatesForVacancy(@PathVariable UUID vacancyId,
                                                        @RequestParam(defaultValue="0") Integer page,
                                                        @RequestParam(defaultValue="10") Integer size) {
        return userProfileService.getCandidatesForVacancy(vacancyId, page, size);
    }

}
