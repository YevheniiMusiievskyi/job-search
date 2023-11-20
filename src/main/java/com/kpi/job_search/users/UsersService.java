package com.kpi.job_search.users;

import com.kpi.job_search.auth.model.AuthUser;
import com.kpi.job_search.image.dto.ImageDto;
import com.kpi.job_search.image.model.Image;
import com.kpi.job_search.users.dto.UserDetailsDto;
import com.kpi.job_search.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.kpi.job_search.auth.TokenService.getUserId;

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