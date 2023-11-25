package com.kpi.job_search.user_profile;

import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.skills.SkillsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SkillsMapper.class)
public interface UserProfileMapper {

    UserProfileDto mapUserProfileToDto(UserProfile userProfile);

    @Mapping(target = "skills", ignore = true)
    UserProfile mapDtoToUserProfile(UserProfileDto userProfileDto);

}
