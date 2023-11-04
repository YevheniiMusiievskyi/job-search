package com.kpi.social_network.config;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864_000_000; // 1 day
    public static final String HEADER_STRING = "Authorization";
    public static final String[] ROUTES_WHITE_LIST = {
            "/api/auth/login", "/api/auth/register", "/ws/**", "/api",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
}