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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

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

        String jwt = authorizationHeader.substring(7);
        String email = jwtService.extractEmail(jwt);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = accountRepository.findByEmail(email);

            if (accountOptional.isPresent() && jwtService.isTokenValid(jwt, accountOptional.get())) {
                Account account = accountOptional.get();
                String role = jwtService.extractClaim(jwt, claims -> (String) claims.get("role"));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        account.getEmail(), null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}