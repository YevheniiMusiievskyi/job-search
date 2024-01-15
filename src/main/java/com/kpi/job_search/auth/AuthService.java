package com.kpi.job_search.auth;

import java.util.Set;

import com.kpi.job_search.auth.dto.AuthUserDTO;
import com.kpi.job_search.auth.dto.UserLoginDTO;
import com.kpi.job_search.auth.dto.UserRegisterDto;
import com.kpi.job_search.auth.model.AuthUser;
import com.kpi.job_search.users.UsersService;
import com.kpi.job_search.users.model.User;
import com.kpi.job_search.users.model.UserRole;
import lombok.RequiredArgsConstructor;
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

    public AuthUserDTO register(UserRegisterDto userDto) throws Exception {
        User user = AuthUserMapper.MAPPER.userRegisterDtoToUser(userDto);
        var roles = userDto.getRole().equals(UserRole.USER) ? Set.of(UserRole.USER) : Set.of(UserRole.USER, UserRole.RECRUITER);
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDetailsService.save(user);
        var loginDTO = new UserLoginDTO(user.getEmail(), user.getPassword());

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
        final var userDetails = userDetailsService.getUserDetailsById(currentUser.getId());
        final String jwt = tokenService.generateToken(currentUser);
        return new AuthUserDTO(jwt, userDetails);
    }
}