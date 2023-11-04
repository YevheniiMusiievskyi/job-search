package com.kpi.social_network.users;

import com.kpi.social_network.users.dto.UserDetailsDto;
import com.kpi.social_network.users.dto.UserShortDto;
import com.kpi.social_network.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetailsDto userToUserDetailsDto(User user);

    UserShortDto userToUserShortDto(User user);
}
