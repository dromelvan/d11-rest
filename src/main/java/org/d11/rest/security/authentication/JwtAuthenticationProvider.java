package org.d11.rest.security.authentication;

import org.d11.rest.api.model.UserDTO;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.UserRepository;
import org.d11.rest.util.D11RestModelMapper;
import org.d11.rest.util.JWT;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private ModelMapper modelMapper = new D11RestModelMapper();

    @Autowired
    public JwtAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Object credentials = authentication.getCredentials();
            String token = credentials != null && !credentials.toString().trim().isEmpty() ? credentials.toString() : "NO_TOKEN_PROVIDED";
            String username = JWT.getUsername(token);

            User user = this.userRepository.findByUsername(username);

            if (user != null && JWT.validateToken(token)) {
                UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
                D11RestAuthentication d11RestAuthentication = new D11RestAuthentication(userDTO, user.getAuthorities(), token);
                d11RestAuthentication.setDetails(authentication.getDetails());
                return d11RestAuthentication;
            }
        } catch (MalformedJwtException e) {
            // No need to do anything
        }

        throw new BadCredentialsException("Invalid token.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthentication.class);
    }

}
