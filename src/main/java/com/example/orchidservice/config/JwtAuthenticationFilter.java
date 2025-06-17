package com.example.orchidservice.config;

import com.example.orchidservice.pojo.Account;
import com.example.orchidservice.repository.AccountRepository;
import com.example.orchidservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authorizationHeader.substring(7);
            String email = jwtService.extractEmail(jwt);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<Account> accountOptional = accountRepository.findByEmail(email);

                if (accountOptional.isPresent() && jwtService.isTokenValid(jwt, accountOptional.get())) {
                    Account account = accountOptional.get();

                    // Add debug logging
                    log.debug("Processing authentication for user: {}", email);
                    log.debug("User role from database: {}", account.getRole().getRoleName());

                    // Create authority with ROLE_ prefix
                    String roleWithPrefix = "ROLE_" + account.getRole().getRoleName().toUpperCase();
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleWithPrefix);

                    log.debug("Created authority: {}", authority.getAuthority());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            account,
                            null,
                            Collections.singletonList(authority)
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.debug("Set authentication with authorities: {}", authToken.getAuthorities());
                }
            }
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/accounts/register") ||
                path.startsWith("/accounts/login") ||
                path.startsWith("/accounts/logout") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/api-docs") ||
                path.startsWith("/webjars");
    }
}