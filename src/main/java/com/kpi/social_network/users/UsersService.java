package com.kpi.social_network.users;

import com.kpi.social_network.auth.model.AuthUser;
import com.kpi.social_network.image.ImageService;
import com.kpi.social_network.image.dto.ImageDto;
import com.kpi.social_network.image.model.Image;
import com.kpi.social_network.users.dto.UserDetailsDto;
import com.kpi.social_network.users.dto.UserShortDto;
import com.kpi.social_network.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.kpi.social_network.auth.TokenService.getUserId;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public AuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository
                .findByEmail(email)
                .map(user -> new AuthUser(user.getId(), user.getEmail(), user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public UserDetailsDto getUserById(UUID id) {
        return usersRepository
                .findById(id)
                .map(userMapper::userToUserDetailsDto)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username"));
    }

    public void save(User user) {
        usersRepository.save(user);
    }

    public void setAvatar(ImageDto avatar) {
        var userAvatar = new Image();
        userAvatar.setId(avatar.getId());
        var user = usersRepository.findById(getUserId())
                .orElseThrow();
        user.setAvatar(userAvatar);
        usersRepository.save(user);
    }
}