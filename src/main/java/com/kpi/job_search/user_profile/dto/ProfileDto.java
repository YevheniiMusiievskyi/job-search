package com.kpi.job_search.user_profile.dto;

import java.util.List;

import com.kpi.job_search.skills.dto.SkillDto;
import lombok.Data;

@Data
public class ProfileDto {

    private String title;
    private String description;
    private List<SkillDto> skills;

}
