package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.d11.rest.Tags;
import org.d11.rest.api.model.UserDTO;
import org.d11.rest.model.D11RestMock;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.UserRepository;
import org.d11.rest.security.authentication.D11RestAuthentication;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Tag(Tags.UNIT_TEST)
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTests {

    @Mock
    private UserRepository userRepository;    
    @Mock
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper = new D11RestModelMapper();
    
    @Test
    public void authenticate() {    	    	
    	when(this.authenticationManager.authenticate(any(Authentication.class))).thenThrow(BadCredentialsException.class);
    	
    	
    	AuthenticationService authenticationService = new AuthenticationService(userRepository, this.authenticationManager);
    	assertThrows(BadCredentialsException.class, () -> authenticationService.authenticate("INVALID", "INVALID"));

    	User user = user();
    	LocalDateTime signInTime = LocalDateTime.now().minus(1,ChronoUnit.DAYS);
    	String ip = user.getCurrentSignInIp();
    	user.setCurrentSignInAt(signInTime);
    	
    	when(this.userRepository.save(any(User.class))).thenReturn(user);
    	when(this.userRepository.findByUsername(user.getUsername())).thenReturn(user);

    	MockHttpServletRequest request = new MockHttpServletRequest();
    	RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    
    	UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
    	when(this.authenticationManager.authenticate(any(Authentication.class))).thenReturn(new D11RestAuthentication(userDTO, user.getAuthorities(), "token"));
    	Authentication authentication = authenticationService.authenticate(D11RestMock.USER, D11RestMock.PASSWORD);

    	assertTrue(authentication.isAuthenticated());    	
    	
    	user = this.userRepository.findByUsername(user.getUsername());
    	assertNotNull(user.getCurrentSignInAt());    	
    	assertTrue(user.getCurrentSignInAt().isAfter(user.getLastSignInAt()));
    	assertEquals(signInTime, user.getLastSignInAt());
    	assertEquals(1, user.getSignInCount());
    	assertEquals(request.getRemoteHost(), user.getCurrentSignInIp());
    	assertEquals(ip, user.getLastSignInIp());
    }
    
}
