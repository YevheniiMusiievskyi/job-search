package com.kpi.job_search.config;

import com.kpi.job_search.auth.TokenService;
import com.kpi.job_search.exceptions.NotFoundException;
import com.kpi.job_search.users.UsersRepository;
import com.kpi.job_search.users.model.User;
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
