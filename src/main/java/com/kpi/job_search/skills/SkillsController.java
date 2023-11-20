package com.kpi.job_search.skills;

import java.util.List;

import com.kpi.job_search.skills.dto.SkillDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;

    @GetMapping
    public List<SkillDto> getAllExistingSkills() {
        return skillsService.getAllExistingSkills();
    }

}
