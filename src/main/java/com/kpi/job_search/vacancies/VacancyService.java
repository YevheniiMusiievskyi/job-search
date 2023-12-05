package com.kpi.job_search.vacancies;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.kpi.job_search.exceptions.ForbiddenException;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.users.UserMapper;
import com.kpi.job_search.users.UsersService;
import com.kpi.job_search.users.model.User;
import com.kpi.job_search.vacancies.dto.VacancyCreationDto;
import com.kpi.job_search.vacancies.dto.VacancyDto;
import com.kpi.job_search.vacancies.dto.VacancyShortDto;
import com.kpi.job_search.vacancies.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    private final UserMapper userMapper;
    private final UsersService usersService;
    private final User currentUser;

    public List<VacancyShortDto> getVacancies(int page, int size) {
        return vacancyRepository.findAll(PageRequest.of(page, size, Sort.by("createdAt").descending()))
                .stream()
                .map(vacancyMapper::vacancyToVacancyShortDto)
                .collect(Collectors.toList());
    }

    public VacancyDto getVacancyDetails(UUID vacancyId) {
        var vacancy = findVacancy(vacancyId);
        var vacancyDto = vacancyMapper.vacancyToVacancyDto(vacancy);
        if (isCreatedByCurrentUser(vacancy)) {
            vacancyDto.setCandidates(userMapper.userToUserShortDto(vacancy.getCandidates()));
        }
        if (vacancy.getCandidates().contains(usersService.getCurrentUser())) {
            vacancyDto.setApplied(true);
        }

        return vacancyDto;
    }

    public void create(VacancyCreationDto vacancyCreationDto) {
        var vacancy = vacancyMapper.vacancyCreationDtoToVacancy(vacancyCreationDto);
        vacancy.setRecruiter(new User(currentUser.getId()));

        vacancyRepository.save(vacancy);
    }

    public void update(UUID vacancyId, VacancyCreationDto vacancyCreationDto) {
        var vacancy = findVacancy(vacancyId);

        if (!isCreatedByCurrentUser(vacancy)) {
            throw new ForbiddenException();
        }
        vacancy.setTitle(vacancyCreationDto.getTitle());
        vacancy.setDescription(vacancyCreationDto.getDescription());

        vacancyRepository.save(vacancy);
    }

    public void applyVacancy(UUID vacancyId) {
        var vacancy = findVacancy(vacancyId);
        if (isCreatedByCurrentUser(vacancy)) {
            throw new ForbiddenException();
        }

        var candidates = vacancy.getCandidates();
        if (candidates.contains(usersService.getCurrentUser())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        candidates.add(new User(currentUser.getId()));
        vacancyRepository.save(vacancy);
    }

    private Vacancy findVacancy(UUID vacancyId) {
        return vacancyRepository.findById(vacancyId)
                .orElseThrow(NotFoundException::new);
    }

    private boolean isCreatedByCurrentUser(Vacancy vacancy) {
        return vacancy.getRecruiter().equals(currentUser);
    }

}
