package com.kpi.job_search.profile;

import java.util.UUID;

import com.kpi.job_search.profile.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileDto getUserProfile(UUID id) {
        return userProfileMapper.mapUserProfileToDto(
                userProfileRepository.findById(id).orElse(null)
        );
    }

}
