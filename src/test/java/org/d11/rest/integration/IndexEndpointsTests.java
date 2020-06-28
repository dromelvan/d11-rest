package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.d11.rest.api.Endpoint;
import org.d11.rest.model.D11RestMock;
import org.d11.rest.model.jpa.User;
import org.d11.rest.security.AuthenticationRequest;
import org.d11.rest.security.AuthenticationResponse;
import org.d11.rest.util.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

public class IndexEndpointsTests extends IntegrationTests {

	@Test
	public void postAuthenticate() throws Exception {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest(D11RestMock.USER, D11RestMock.PASSWORD);
		
		assertForbidden(post(Endpoint.AUTHENTICATE), INVALID_TOKEN, authenticationRequest);

		User user = user();
		// This saves the user.
  	    token(user);
  	    
  	    MvcResult mvcResult = assertOk(post(Endpoint.AUTHENTICATE), INVALID_TOKEN, authenticationRequest);
  	  
  	    AuthenticationResponse authenticationResponse = readValue(mvcResult, AuthenticationResponse.class);
  	    
  	    assertNotNull(authenticationResponse.getToken());
  	    assertEquals(user.getUsername(), JWT.getUsername(authenticationResponse.getToken()));
  	    assertTrue(JWT.validateToken(authenticationResponse.getToken()));  	    
	}
	
}
