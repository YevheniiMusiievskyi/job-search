package com.kpi.social_network.auth;

import com.kpi.social_network.auth.dto.UserRegisterDto;
import com.kpi.social_network.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserMapper {
    AuthUserMapper MAPPER = Mappers.getMapper(AuthUserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    User userRegisterDtoToUser(UserRegisterDto userDto);
}
