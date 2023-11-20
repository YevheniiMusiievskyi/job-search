package com.kpi.job_search.skills.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kpi.job_search.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "skills")
@Data
@EqualsAndHashCode(callSuper = true)
public class Skill extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
