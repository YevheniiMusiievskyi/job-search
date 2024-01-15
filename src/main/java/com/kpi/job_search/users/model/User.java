package com.kpi.job_search.users.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.image.model.Image;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.vacancies.model.Vacancy;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=true, onlyExplicitlyIncluded = true)
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "avatar_id")
    private Image avatar;

    @ManyToMany(mappedBy = "candidates")
    private List<Vacancy> appliedVacancies = Collections.emptyList();

    @OneToMany(mappedBy = "recruiter")
    private List<Vacancy> createdVacancies = Collections.emptyList();

    public User(UUID id) {
        super(id);
    }
}
