package com.kpi.job_search.user_profile.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Contacts {
    private String phone;
}
