package com.kpi.job_search.user_profile;

import com.kpi.job_search.image.ImageMapper;
import com.kpi.job_search.user_profile.dto.ContactsDto;
import com.kpi.job_search.user_profile.dto.CreateProfileDto;
import com.kpi.job_search.user_profile.dto.ProfileDto;
import com.kpi.job_search.user_profile.dto.UserProfileDto;
import com.kpi.job_search.user_profile.model.Contacts;
import com.kpi.job_search.user_profile.model.Profile;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.skills.SkillsMapper;
import com.kpi.job_search.users.UserMapper;
import com.kpi.job_search.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SkillsMapper.class, UserMapper.class, ImageMapper.class})
public interface UserProfileMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "contacts.email")
    @Mapping(source = "user.fullName", target = "contacts.fullName")
    @Mapping(source = "contacts.phone", target = "contacts.phone")
    UserProfileDto mapUserProfileToDto(UserProfile userProfile);

    ProfileDto mapProfileToDto(Profile profile);

    @Mapping(target = "skills", ignore = true)
    Profile mapCreateDtoToUserProfile(CreateProfileDto userProfileDto);

    @Mapping(target = "skills", ignore = true)
    void updateProfileFromDto(ProfileDto userProfileDto, @MappingTarget Profile profile);

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "contacts.phone", target = "phone")
    ContactsDto mapUserProfileContactsToDto(UserProfile userProfile);

    void updateContactsFromDto(ContactsDto contactsDto, @MappingTarget Contacts contacts);

    @Mapping(target = "avatar", ignore = true)
    void updateUserFromContactsDto(ContactsDto contactsDto, @MappingTarget User user);

}
