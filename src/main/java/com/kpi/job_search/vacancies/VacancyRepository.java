package com.kpi.job_search.vacancies;

import java.util.UUID;

import com.kpi.job_search.vacancies.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

}
