package com.kpi.job_search.vacancies.model;

import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.users.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "vacancies")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "title", "description" })
public class Vacancy extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10_000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    @ManyToMany
    @JoinTable(name = "vacancies_candidates",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"vacancy_id", "candidate_id"})
    )
    private List<User> candidates = Collections.emptyList();

}
