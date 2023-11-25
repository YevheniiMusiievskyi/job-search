package com.kpi.job_search.user_profile;

import java.util.UUID;

import com.kpi.job_search.auth.TokenService;
import com.kpi.job_search.exceptions.ForbiddenException;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.skills.SkillsService;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserProfileDto saveUserProfile(UserProfileDto userProfileDto) {
        var userProfile = userProfileDto.getId() != null ? createUserProfile(userProfileDto) : updateUserProfile(userProfileDto);
        return userProfileMapper.mapUserProfileToDto(userProfile);
    }

    private UserProfile createUserProfile(UserProfileDto userProfileDto) {
        return userProfileRepository.findById(userProfileDto.getId())
                .filter(profile -> {
                    if (!profile.getUser().getId().equals(TokenService.getUserId())) {
                        throw new ForbiddenException();
                    }
                    return true;
                })
                .map(profile -> {
                    profile.setTitle(userProfileDto.getTitle());
                    profile.setDescription(userProfileDto.getDescription());
                    var skills = skillsService.saveSkills(userProfileDto.getSkills());
                    profile.setSkills(skills);

                    return userProfileRepository.save(profile);
                })
                .orElseThrow(NotFoundException::new);
    }

    private UserProfile updateUserProfile(UserProfileDto userProfileDto) {
        var userProfile = userProfileMapper.mapDtoToUserProfile(userProfileDto);
        var skills = skillsService.saveSkills(userProfileDto.getSkills());
        userProfile.setSkills(skills);
        userProfile.setUser(new User(TokenService.getUserId()));

        return userProfileRepository.save(userProfile);
    }

}
