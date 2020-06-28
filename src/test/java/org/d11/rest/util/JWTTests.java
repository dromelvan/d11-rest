package org.d11.rest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.model.jpa.User;
import org.d11.rest.util.JWT;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.MalformedJwtException;

@Tag(Tags.UNIT_TEST)
public class JWTTests {
	
	@Test
	public void testJwtService() {
		User user = new User("username", "password");
		String token = JWT.generateToken(user);
		
		assertNotNull(token);
		assertNotEquals("", token);
		
		String username = JWT.getUsername(token);
		assertNotNull(username);
		assertEquals(username, user.getUsername());
				
		boolean valid = JWT.validateToken(token);
		assertTrue(valid);
		
		String invalidToken = token + "invalid";
		username = JWT.getUsername(invalidToken);
		assertNull(username);
		
		valid = JWT.validateToken(invalidToken);
		assertFalse(valid);
		
		final String malformedToken = "MALFORMED_TOKEN";
		assertThrows(MalformedJwtException.class, () -> JWT.getUsername(malformedToken));
		assertThrows(MalformedJwtException.class, () -> JWT.validateToken(malformedToken));		
	}
	
}
