package org.d11.rest.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.d11.rest.api.model.UserDTO;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.UserRepository;
import org.d11.rest.security.authentication.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthenticationService extends D11RestService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Authenticates a user and generates a valid JWT.
     * 
     * @param username The username of the user that will be authenticated.
     * @param password The password the user will be authenticated with.
     * @return A D11RestAuthentication containing a valid JWT for the authenticated
     *         user and the user itself.
     * @throws BadCredentialsException if authentication fails.
     */
    public Authentication authenticate(String username, String password) throws BadCredentialsException {
        Authentication authentication = this.authenticationManager.authenticate(new UserAuthentication(username, password));

        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        User user = this.userRepository.findByUsername(userDTO.getUsername());

        LocalDateTime lastSignInAt = user.getCurrentSignInAt();
        user.setCurrentSignInAt(LocalDateTime.now());
        user.setLastSignInAt((lastSignInAt != null ? lastSignInAt : user.getCurrentSignInAt()));
        user.setSignInCount(user.getSignInCount() + 1);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String lastSignInIp = user.getCurrentSignInIp();
        user.setCurrentSignInIp(request.getRemoteHost());
        user.setLastSignInIp((lastSignInIp != null ? lastSignInIp : user.getCurrentSignInIp()));

        user = this.userRepository.save(user);

        return authentication;
    }

}
