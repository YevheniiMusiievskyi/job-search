package com.kpi.job_search.config;

import com.kpi.job_search.auth.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static com.kpi.job_search.config.SecurityConstants.HEADER_STRING;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String token = getToken(request);
        if (token == null){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    // TODO: improve code
    private String getToken(HttpServletRequest request) {
        String cookieToken = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (var cookie : cookies) {
                if (cookie.getName().equals(HEADER_STRING))
                    cookieToken = cookie.getValue();
            }
        }

        String token = StringUtils.firstNonBlank(
                request.getHeader(HEADER_STRING),
                cookieToken
        );

        return Optional.ofNullable(token)
                .map(t -> t.replace("Bearer ", ""))
                .orElse(null);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (token != null) {
            // parse the token.
            String user = tokenService.extractUserId(token);
            var authorities = tokenService.getAuthorities(token);
            if (user != null && !tokenService.isTokenExpired(token)) {
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
            return null;
        }
        return null;
    }
}