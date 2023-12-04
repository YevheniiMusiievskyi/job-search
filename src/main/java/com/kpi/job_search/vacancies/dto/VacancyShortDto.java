package com.kpi.job_search.vacancies.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class VacancyShortDto implements VacancyCandidatesCount {
    private UUID id;
    private String title;
    private String description;
    private int candidatesCount;

}
