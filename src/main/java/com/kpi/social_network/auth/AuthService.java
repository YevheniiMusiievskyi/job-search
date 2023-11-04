package com.kpi.social_network.auth;

import com.kpi.social_network.auth.dto.UserRegisterDto;
import com.kpi.social_network.auth.model.AuthUser;
import com.kpi.social_network.auth.dto.AuthUserDTO;
import com.kpi.social_network.auth.dto.UserLoginDTO;
import com.kpi.social_network.users.model.User;
import com.kpi.social_network.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsersService userDetailsService;

    public AuthUserDTO register(UserRegisterDto userDto) throws Exception {
        User user = AuthUserMapper.MAPPER.userRegisterDtoToUser(userDto);
        var loginDTO = new UserLoginDTO(user.getEmail(), user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDetailsService.save(user);
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