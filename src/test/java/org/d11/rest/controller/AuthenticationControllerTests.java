package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.d11.rest.Tags;
import org.d11.rest.api.model.UserDTO;
import org.d11.rest.model.jpa.User;
import org.d11.rest.security.AuthenticationRequest;
import org.d11.rest.security.AuthenticationResponse;
import org.d11.rest.security.authentication.D11RestAuthentication;
import org.d11.rest.service.AuthenticationService;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

@Tag(Tags.UNIT_TEST)
public class AuthenticationControllerTests {

	private ModelMapper modelMapper = new D11RestModelMapper();
	
	@Test
	public void postAuthenticate() {		
		String token = "VALID_TOKEN";
		
		User user = user();
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		
		AuthenticationService authenticationService = mock(AuthenticationService.class);
		when(authenticationService.authenticate(anyString(), anyString())).thenReturn(new D11RestAuthentication(userDTO, user.getAuthorities(), token));
		
		AuthenticationController authenticationController = new AuthenticationController(authenticationService);
		ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(new AuthenticationRequest(user.getUsername(), user.getPassword()));
		
		assertEquals(AuthenticationResponse.class, responseEntity.getBody().getClass());
		
		AuthenticationResponse authenticationResponse = responseEntity.getBody();
		
		assertNotNull(authenticationResponse);
		assertNotNull(authenticationResponse.getToken());
		assertEquals(token, authenticationResponse.getToken());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());		
		
		when(authenticationService.authenticate(anyString(), anyString())).thenThrow(BadCredentialsException.class);
		assertThrows(BadCredentialsException.class, () -> authenticationController.authenticate(new AuthenticationRequest("INVALID", "INVALID")));
	}
	
}
