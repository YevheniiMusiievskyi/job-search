package com.kpi.job_search.users;

import java.util.List;

import com.kpi.job_search.users.dto.UserDetailsDto;
import com.kpi.job_search.users.dto.UserShortDto;
import com.kpi.job_search.users.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetailsDto userToUserDetailsDto(User user);

    List<UserShortDto> userToUserShortDto(List<User> users);

    UserShortDto userToUserShortDto(User user);
}
