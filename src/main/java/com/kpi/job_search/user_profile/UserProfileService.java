package com.kpi.job_search.user_profile;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.auth.TokenService;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.skills.SkillsService;
import com.kpi.job_search.user_profile.dto.ContactsDto;
import com.kpi.job_search.user_profile.dto.ProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.user_profile.model.Contacts;
import com.kpi.job_search.user_profile.model.Profile;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.users.UsersService;
import com.kpi.job_search.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final SkillsService skillsService;

    private final UsersService usersService;

    public UserProfileDto getUserProfile(UUID id) {
        return userProfileMapper.mapUserProfileToDto(
                userProfileRepository.findByUserId(id).orElse(null)
        );
    }

    public void save(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public ProfileDto updateUserProfile(ProfileDto userProfileDto) {
        var userProfile = userProfileRepository.findByUserId(TokenService.getUserId())
                .orElseGet(this::createUserProfile);

        var profile = userProfile.getProfile();
        userProfileMapper.updateProfileFromDto(userProfileDto, profile);
        var skills = skillsService.saveSkills(userProfileDto.getSkills());
        profile.setSkills(skills);

        return userProfileMapper.mapProfileToDto(userProfileRepository.save(userProfile).getProfile());
    }

    public UserProfile createUserProfile() {
        var userProfile = new UserProfile();
        userProfile.setUser(new User(TokenService.getUserId()));
        userProfile.setProfile(new Profile());
        return userProfile;
    }

    public ContactsDto updateContacts(ContactsDto contactsDto) {
        var userProfile = userProfileRepository.findByUserId(TokenService.getUserId())
                .orElseThrow(NotFoundException::new);

        var user = userProfile.getUser();
        userProfileMapper.updateUserFromContactsDto(contactsDto, user);
        usersService.save(user);

        var contacts = userProfile.getContacts();
        if (contacts == null) {
            contacts = new Contacts();
            userProfile.setContacts(contacts);
        }
        userProfileMapper.updateContactsFromDto(contactsDto, contacts);

        return userProfileMapper.mapUserProfileContactsToDto(userProfileRepository.save(userProfile));
    }

    public List<UserProfileDto> getCandidates(int page, int size) {
        return userProfileRepository.findAllByOrderByUpdatedAtDesc(PageRequest.of(page, size))
                .stream()
                .map(userProfileMapper::mapUserProfileToDto)
                .toList();
    }

    public List<UserProfileDto> getCandidatesForVacancy(UUID vacancyId, int page, int size) {
        return userProfileRepository.findCandidatesByVacancyId(vacancyId, PageRequest.of(page, size))
                .stream()
                .map(userProfileMapper::mapUserProfileToDto)
                .toList();
    }

    public UserProfileDto getCandidate(UUID userId) {
        return userProfileRepository.findByUserId(userId)
                .map(userProfileMapper::mapUserProfileToDto)
                .map(userProfile -> {
                    if (!isCurrentUserHasAccessToContacts(userId)) {
                        userProfile.setContacts(null);
                    }
                    return userProfile;
                })
                .orElseThrow(NotFoundException::new);
    }

    private boolean isCurrentUserHasAccessToContacts(UUID userId) {
        var currentUserId = TokenService.getUserId();
        if (userId.equals(currentUserId)) {
            return true;
        }

        return usersService.getUserById(userId)
                .getAppliedVacancies()
                .stream()
                .anyMatch(v -> v.getRecruiter().getId().equals(currentUserId));
    }


}
