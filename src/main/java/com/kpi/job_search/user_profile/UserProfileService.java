package com.kpi.job_search.user_profile;

import java.util.List;
import java.util.UUID;

import com.kpi.job_search.auth.TokenService;
import com.kpi.job_search.exceptions.ForbiddenException;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.skills.SkillsService;
import com.kpi.job_search.user_profile.dto.ContactsDto;
import com.kpi.job_search.user_profile.dto.ProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.user_profile.model.Contacts;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.users.UsersService;
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

    /*public UserProfileDto createUserProfile(UserProfileDto userProfileDto) {
        var profile = userProfileMapper.mapCreateDtoToUserProfile(userProfileDto);
        var skills = skillsService.saveSkills(userProfileDto.getSkills());
        profile.setSkills(skills);

        var userProfile = new UserProfile();
        userProfile.setUser(new User(TokenService.getUserId()));

        return userProfileMapper.mapUserProfileToDto(userProfileRepository.save(userProfile));
    }*/

    public ProfileDto updateUserProfile(ProfileDto userProfileDto) {
        var userProfile = findUserProfile();

        var profile = userProfile.getProfile();
        userProfileMapper.updateProfileFromDto(userProfileDto, profile);
        var skills = skillsService.saveSkills(userProfileDto.getSkills());
        profile.setSkills(skills);

        return userProfileMapper.mapProfileToDto(userProfileRepository.save(userProfile).getProfile());
    }

    public ContactsDto updateContacts(ContactsDto contactsDto) {
        var userProfile = findUserProfile();

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

    private UserProfile findUserProfile() {
        var userId = TokenService.getUserId();
        var userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(NotFoundException::new);

        if (!userProfile.getUser().getId().equals(userId)) {
            throw new ForbiddenException();
        }

        return userProfile;
    }

    public List<UserProfileDto> getCandidates(int page, int size) {
        return userProfileRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(userProfileMapper::mapUserProfileToDto)
                .toList();
    }

    public UserProfileDto getCandidate(UUID userId) {
        return userProfileRepository.findByUserId(userId)
                .map(userProfileMapper::mapUserProfileToDto)
                .orElseThrow(NotFoundException::new);
    }

}
