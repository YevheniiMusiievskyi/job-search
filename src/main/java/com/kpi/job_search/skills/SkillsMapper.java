package com.kpi.job_search.skills;

import java.util.List;

import com.kpi.job_search.skills.dto.SkillDto;
import com.kpi.job_search.skills.model.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillsMapper {

    List<SkillDto> mapSkillsToDto(List<Skill> skills);

}
