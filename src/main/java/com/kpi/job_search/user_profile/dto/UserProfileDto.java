package com.kpi.job_search.user_profile.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UserProfileDto {

    private UUID id;
    private UUID userId;
    private ProfileDto profile;
    private ContactsDto contacts;

}
