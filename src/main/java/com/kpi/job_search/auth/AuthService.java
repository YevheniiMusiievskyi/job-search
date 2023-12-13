package com.kpi.job_search.auth;

import com.kpi.job_search.auth.dto.UserRegisterDto;
import com.kpi.job_search.auth.model.AuthUser;
import com.kpi.job_search.auth.dto.AuthUserDTO;
import com.kpi.job_search.auth.dto.UserLoginDTO;
import com.kpi.job_search.user_profile.UserProfileService;
import com.kpi.job_search.user_profile.model.UserProfile;
import com.kpi.job_search.users.model.User;
import com.kpi.job_search.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsersService userDetailsService;
    private final UserProfileService userProfileService;

    public AuthUserDTO register(UserRegisterDto userDto) throws Exception {
        User user = AuthUserMapper.MAPPER.userRegisterDtoToUser(userDto);
        var loginDTO = new UserLoginDTO(user.getEmail(), user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        var savedUser = userDetailsService.save(user);
        var userProfile = new UserProfile();
        userProfile.setUser(savedUser);
        userProfileService.save(userProfile);

        return login(loginDTO);
    }

    public AuthUserDTO login(UserLoginDTO user) throws Exception {
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        var currentUser = (AuthUser)auth.getPrincipal();
        final var userDetails = userDetailsService.getUserById(currentUser.getId());
        final String jwt = tokenService.generateToken(currentUser);
        return new AuthUserDTO(jwt, userDetails);
    }
}