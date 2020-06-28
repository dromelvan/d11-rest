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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private ModelMapper modelMapper = new D11RestModelMapper();

    @Autowired
    public UserAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NO_USERNAME_PROVIDED" : authentication.getName();
        String password = (authentication.getCredentials() != null ? authentication.getCredentials().toString() : "NO_PASSWORD_PROVIDED");

        User user = this.userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = JWT.generateToken(user);

            UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
            D11RestAuthentication d11RestAuthentication = new D11RestAuthentication(userDTO, user.getAuthorities(), token);
            d11RestAuthentication.setDetails(authentication.getDetails());

            return d11RestAuthentication;
        }

        throw new BadCredentialsException("Invalid username or password.");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UserAuthentication.class);
    }

}
