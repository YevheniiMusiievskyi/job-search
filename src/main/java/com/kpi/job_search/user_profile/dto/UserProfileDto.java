package com.kpi.job_search.user_profile.dto;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.skills.dto.SkillDto;
import lombok.Data;

@Data
public class UserProfileDto {

    private UUID id;
    private String title;
    private String description;
    private List<SkillDto> skills;

}
