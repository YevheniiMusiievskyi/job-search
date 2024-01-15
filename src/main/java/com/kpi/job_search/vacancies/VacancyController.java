package com.kpi.job_search.vacancies;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.vacancies.dto.VacancyCreationDto;
import com.kpi.job_search.vacancies.dto.VacancyDto;
import com.kpi.job_search.vacancies.dto.VacancyShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyShortDto> getVacancies(@RequestParam(defaultValue="0") Integer page,
                                              @RequestParam(defaultValue="10") Integer size) {
        return vacancyService.getVacancies(page, size);
    }

    @GetMapping("/{vacancyId}")
    public VacancyDto getVacancy(@PathVariable UUID vacancyId) {
        return vacancyService.getVacancyDetails(vacancyId);
    }

    @PostMapping
    public void create(@RequestBody VacancyCreationDto vacancyCreationDto) {
        vacancyService.create(vacancyCreationDto);
    }

    @PutMapping("/{vacancyId}")
    public void update(@PathVariable UUID vacancyId, @RequestBody VacancyCreationDto vacancyCreationDto) {
        vacancyService.update(vacancyId, vacancyCreationDto);
    }

    @DeleteMapping("/{vacancyId}")
    public void delete(@PathVariable UUID vacancyId) {
        vacancyService.delete(vacancyId);
    }

    @PostMapping("/apply/{vacancyId}")
    public void applyVacancy(@PathVariable UUID vacancyId) {
        vacancyService.applyVacancy(vacancyId);
    }

}
