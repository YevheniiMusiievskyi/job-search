package com.kpi.job_search.users.model;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.image.model.Image;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "avatar_id")
    private Image avatar;
}
