package com.kpi.job_search.user_profile;

import java.util.UUID;

import com.kpi.job_search.auth.TokenService;
import com.kpi.job_search.exceptions.ForbiddenException;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.skills.SkillsService;
import com.kpi.job_search.user_profile.dto.CreateUserProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final SkillsService skillsService;

    public UserProfileDto getUserProfile(UUID id) {
        return userProfileMapper.mapUserProfileToDto(
                userProfileRepository.findByUserId(id).orElse(null)
        );
    }

    public UserProfileDto createUserProfile(CreateUserProfileDto userProfileDto) {
        var userProfile = userProfileMapper.mapCreateDtoToUserProfile(userProfileDto);
        var skills = skillsService.saveSkills(userProfileDto.getSkills());
        userProfile.setSkills(skills);
        userProfile.setUser(new User(TokenService.getUserId()));

        return userProfileMapper.mapUserProfileToDto(userProfileRepository.save(userProfile));
    }

    public UserProfileDto updateUserProfile(UserProfileDto userProfileDto) {
        var profile = userProfileRepository.findById(userProfileDto.getId())
                .orElseThrow(NotFoundException::new);

        if (!profile.getUser().getId().equals(TokenService.getUserId())) {
            throw new ForbiddenException();
        }

        userProfileMapper.updateUserProfileFromDto(userProfileDto, profile);
        var skills = skillsService.saveSkills(userProfileDto.getSkills());
        profile.setSkills(skills);

        return userProfileMapper.mapUserProfileToDto(userProfileRepository.save(profile));
    }

}
