package com.kpi.social_network.config;

import com.kpi.social_network.auth.TokenService;
import com.kpi.social_network.exceptions.NotFoundException;
import com.kpi.social_network.users.UsersRepository;
import com.kpi.social_network.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class RequestScopeConfig {

	private final UsersRepository usersRepository;

	@Bean
	@RequestScope
	public User currentUser() {
		UUID id = TokenService.getUserId();
		return usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("user not found"));
	}
}
