package org.d11.rest.security;

import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.d11.rest.Tags;
import org.d11.rest.model.D11RestMock;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.UserRepository;
import org.d11.rest.security.authentication.JwtAuthentication;
import org.d11.rest.security.authentication.JwtAuthenticationProvider;
import org.d11.rest.security.authentication.UserAuthentication;
import org.d11.rest.security.authentication.UserAuthenticationProvider;
import org.d11.rest.util.JWT;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

@Tag(Tags.UNIT_TEST)
@ExtendWith(MockitoExtension.class)
public class AuthenticationProviderTests {

    @Mock
    private UserRepository userRepository;    

    @Test
    public void userNamePasswordAuthenticationProviderAuthenticate() {    	    	
    	UserAuthenticationProvider provider = new UserAuthenticationProvider(this.userRepository);
    	
    	assertTrue(provider.supports(UserAuthentication.class));
    	
    	User user = user();
    	when(this.userRepository.findByUsername(anyString())).thenReturn(null);
    	when(this.userRepository.findByUsername(user.getUsername())).thenReturn(user);

    	UserAuthentication userAuthentication = new UserAuthentication(D11RestMock.USER, D11RestMock.PASSWORD);
    	Authentication authentication = provider.authenticate(userAuthentication);
    	assertTrue(authentication.isAuthenticated());
    	
    	String token = (String)authentication.getCredentials();    	
    	assertEquals(user.getUsername(), JWT.getUsername(token));
    	assertTrue(JWT.validateToken(token));
    	
    	assertThrows(BadCredentialsException.class, () -> provider.authenticate(new UserAuthentication(D11RestMock.USER, null)));    	
    	assertThrows(BadCredentialsException.class, () -> provider.authenticate(new UserAuthentication(null, D11RestMock.PASSWORD)));
    }
    
    @Test
    public void jwtAuthenticationProviderAuthenticate() {
    	User user = user();
    	JwtAuthenticationProvider provider = new JwtAuthenticationProvider(this.userRepository);

    	assertTrue(provider.supports(JwtAuthentication.class));
    	
    	when(this.userRepository.findByUsername(anyString())).thenReturn(null);
    	when(this.userRepository.findByUsername(user.getUsername())).thenReturn(user);

    	String token = JWT.generateToken(user);
    	
    	Authentication authentication = provider.authenticate(new JwtAuthentication(token));
    	assertTrue(authentication.isAuthenticated());
    	assertEquals(token, authentication.getCredentials());
    	assertEquals(user.getUsername(), JWT.getUsername((String)authentication.getCredentials()));
    	assertTrue(JWT.validateToken((String)authentication.getCredentials()));
    	
    	assertThrows(BadCredentialsException.class, () -> provider.authenticate(new JwtAuthentication(token + "0")));
    	
    	user.setUsername("INVALID_USER");
    	String invalidToken = JWT.generateToken(user);
    	assertThrows(BadCredentialsException.class, () -> provider.authenticate(new JwtAuthentication(invalidToken)));
    	
    	assertThrows(BadCredentialsException.class, () -> provider.authenticate(new JwtAuthentication(null))); 
    	assertThrows(BadCredentialsException.class, () -> provider.authenticate(new JwtAuthentication(""))); 
    }
    
}
