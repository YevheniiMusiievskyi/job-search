package com.kpi.job_search.vacancies;

import com.kpi.job_search.users.UserMapper;
import com.kpi.job_search.vacancies.dto.VacancyCandidatesCount;
import com.kpi.job_search.vacancies.dto.VacancyCreationDto;
import com.kpi.job_search.vacancies.dto.VacancyDto;
import com.kpi.job_search.vacancies.dto.VacancyShortDto;
import com.kpi.job_search.vacancies.model.Vacancy;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface VacancyMapper {

    @Mapping(target = "candidates", ignore = true)
    VacancyDto vacancyToVacancyDto(Vacancy vacancy);

    VacancyShortDto vacancyToVacancyShortDto(Vacancy vacancy);

    Vacancy vacancyCreationDtoToVacancy(VacancyCreationDto vacancyCreationDto);

    @AfterMapping
    default void afterVacancyCandidatesCountMapping(Vacancy vacancy, @MappingTarget VacancyCandidatesCount vacancyDto) {
        vacancyDto.setCandidatesCount(vacancy.getCandidates().size());
    }

}
