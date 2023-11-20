package com.kpi.job_search.user_profile.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.skills.model.Skill;
import com.kpi.job_search.users.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user_profiles")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends BaseEntity {

    private String title;

    private String description;

    @ManyToMany
    @JoinTable(name = "user_profiles_skills",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
