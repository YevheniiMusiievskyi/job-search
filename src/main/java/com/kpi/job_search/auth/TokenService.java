package com.kpi.job_search.auth;

import com.kpi.job_search.auth.model.AuthUser;
import com.kpi.job_search.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.kpi.job_search.config.SecurityConstants.EXPIRATION_TIME;

@Service
public class TokenService {

    @Value(value = "${jwt.secret-key}")
    private String SECRET_KEY;

    private static final String AUTHORITIES = "authorities";

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Set<GrantedAuthority> getAuthorities(String token) {
        return extractClaim(token, claims -> ((List<Object>) claims.get(AUTHORITIES))
                .stream()
                .map(Object::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet())
        );
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(AuthUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put(AUTHORITIES, authorities);
        return createToken(claims, userDetails.getId());
    }

    private String createToken(Map<String, Object> claims, UUID subject) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static UUID getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var currentUserId = (String) auth.getPrincipal();

        try {
            return UUID.fromString(currentUserId);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}