package com.kpi.job_search.users;

import com.kpi.job_search.image.dto.ImageDto;
import com.kpi.job_search.users.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.kpi.job_search.auth.TokenService.getUserId;

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
