package com.kpi.job_search.user_profile.dto;

import com.kpi.job_search.image.dto.ImageDto;
import lombok.Data;

@Data
public class ContactsDto {

    private String fullName;
    private String email;
    private String phone;
    private ImageDto avatar;

}
