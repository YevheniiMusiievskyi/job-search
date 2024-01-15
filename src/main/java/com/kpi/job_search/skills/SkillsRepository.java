package com.kpi.job_search.skills;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.skills.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillsRepository extends JpaRepository<Skill, UUID> {

    @Query("SELECT s FROM Skill s WHERE lower(s.name) IN (:skills)")
    List<Skill> findAllByNameIn(@Param("skills") List<String> skills);

}
