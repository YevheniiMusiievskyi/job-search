package com.kpi.job_search.profile;

import com.kpi.job_search.profile.dto.UserProfileDto;
import com.kpi.job_search.profile.model.UserProfile;
import com.kpi.job_search.skills.SkillsMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SkillsMapper.class)
public interface UserProfileMapper {

    UserProfileDto mapUserProfileToDto(UserProfile userProfile);

}
