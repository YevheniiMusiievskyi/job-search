package com.kpi.job_search.user_profile.model;

import java.util.Collections;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.kpi.job_search.skills.model.Skill;
import lombok.Data;

@Data
@Embeddable
public class Profile {

    private String title;

    private String description;

    @ManyToMany
    @JoinTable(name = "user_profiles_skills",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = Collections.emptyList();

}
