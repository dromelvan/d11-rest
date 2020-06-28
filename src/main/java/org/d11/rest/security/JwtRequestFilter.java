package org.d11.rest.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.d11.rest.security.authentication.JwtAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtRequestFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;
    private final static Pattern AUTHORIZATION_HEADER_PATTERN = Pattern.compile("Bearer (.*)");

    public JwtRequestFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            Matcher matcher = AUTHORIZATION_HEADER_PATTERN.matcher(authorizationHeader);
            if (matcher.matches()) {
                String token = matcher.group(1);
                try {
                    Authentication authentication = this.authenticationManager.authenticate(new JwtAuthentication(token));

                    if (authentication.isAuthenticated() && SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (AuthenticationException e) {
                    // No need to do anything here.
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
