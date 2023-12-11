package com.kpi.job_search.user_profile.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.users.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user_profiles")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends BaseEntity {

    @Embedded
    private Profile profile;

    @Embedded
    private Contacts contacts;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
