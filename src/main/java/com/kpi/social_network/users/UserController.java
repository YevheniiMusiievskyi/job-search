package com.kpi.social_network.users;

import com.kpi.social_network.image.dto.ImageDto;
import com.kpi.social_network.users.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.kpi.social_network.auth.TokenService.getUserId;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UsersService userDetailsService;

    @GetMapping
    public UserDetailsDto getUser() {
        return userDetailsService.getUserById(getUserId());
    }

    @PostMapping("/avatar")
    public void setAvatar(@RequestBody ImageDto avatar) {
        userDetailsService.setAvatar(avatar);
    }
}
