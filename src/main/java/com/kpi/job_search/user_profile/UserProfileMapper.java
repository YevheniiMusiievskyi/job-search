package com.kpi.job_search.user_profile;

import com.kpi.job_search.user_profile.dto.CreateUserProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.skills.SkillsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = SkillsMapper.class)
public interface UserProfileMapper {

    UserProfileDto mapUserProfileToDto(UserProfile userProfile);

    @Mapping(target = "skills", ignore = true)
    UserProfile mapCreateDtoToUserProfile(CreateUserProfileDto userProfileDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", ignore = true)
    void updateUserProfileFromDto(UserProfileDto userProfileDto, @MappingTarget UserProfile profile);

}
