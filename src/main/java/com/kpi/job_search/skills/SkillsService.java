package com.kpi.job_search.skills;

import java.util.List;

import com.kpi.job_search.skills.dto.SkillDto;
import com.kpi.job_search.skills.model.Skill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository skillsRepository;
    private final SkillsMapper skillsMapper;

    public List<SkillDto> getAllExistingSkills() {
        return skillsMapper.mapSkillsToDto(skillsRepository.findAll());
    }

    @Transactional
    public List<Skill> saveSkills(List<SkillDto> skillsDto) {
        var skills = skillsMapper.mapDtoToSkills(skillsDto);
        var skillNames = skills
                .stream()
                .map(Skill::getName)
                .map(String::toLowerCase)
                .toList();

        var existingSkills = skillsRepository.findAllByNameIn(skillNames);
        skills = skills.stream()
                .map(s -> {
                    if (s.getId() != null) {
                        return s;
                    }

                    return existingSkills.stream()
                            .filter(existingSkill -> StringUtils.equalsIgnoreCase(existingSkill.getName(), s.getName()))
                            .findFirst()
                            .orElse(s);
                })
                .toList();

        return skillsRepository.saveAll(skills);
    }

}
