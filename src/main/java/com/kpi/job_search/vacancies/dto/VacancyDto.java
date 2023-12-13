package com.kpi.job_search.vacancies.dto;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.users.dto.UserShortDto;
import lombok.Data;

@Data
public class VacancyDto implements VacancyCandidatesCount {

    private UUID id;
    private String title;
    private String description;
    private int candidatesCount;
    private UserShortDto recruiter;
    private List<UserShortDto> candidates;
    private boolean applied;

}
