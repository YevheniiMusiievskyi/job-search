package com.kpi.job_search.user_profile.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.kpi.job_search.skills.dto.SkillDto;
import lombok.Data;

@Data
public class ProfileDto {

    private String title;
    private String description;
    @NotNull
    private List<SkillDto> skills;

}
