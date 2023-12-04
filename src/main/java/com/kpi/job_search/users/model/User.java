package com.kpi.job_search.users.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.image.model.Image;
import com.kpi.job_search.vacancies.model.Vacancy;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=true, onlyExplicitlyIncluded = true)
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "avatar_id")
    private Image avatar;

    @ManyToMany(mappedBy = "candidates")
    private List<Vacancy> appliedVacancies = new ArrayList<>();

    @OneToMany(mappedBy = "recruiter")
    private List<Vacancy> createdVacancies = new ArrayList<>();

    public User(UUID id) {
        super(id);
    }
}
