package com.kpi.job_search.skills;

import java.util.UUID;

import com.kpi.job_search.skills.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skill, UUID> {



}
