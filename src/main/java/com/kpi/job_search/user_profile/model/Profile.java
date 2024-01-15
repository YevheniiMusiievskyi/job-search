package com.kpi.job_search.user_profile.model;

import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import com.kpi.job_search.skills.model.Skill;
import lombok.Data;

@Data
@Embeddable
public class Profile {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10_000)
    private String description;

    @ManyToMany
    @JoinTable(name = "user_profiles_skills",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_profile_id", "skill_id"})
    )
    private List<Skill> skills = Collections.emptyList();

}
