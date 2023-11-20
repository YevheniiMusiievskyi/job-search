package com.kpi.job_search.skills;

import java.util.List;

import com.kpi.job_search.skills.dto.SkillDto;
import com.kpi.job_search.skills.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository skillsRepository;
    private final SkillsMapper skillsMapper;

    public List<SkillDto> getAllExistingSkills() {
        return skillsMapper.mapSkillsToDto(skillsRepository.findAll());
    }

}
