package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.d11.rest.Tags;
import org.d11.rest.api.model.UserDTO;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class UserTests {

    @Test
    public void isValid() {
    	User user = user();
        
        assertTrue(user.isValid());
        
        user.setUsername("");
        assertFalse(user.isValid());
        user.setUsername(null);
        assertFalse(user.isValid());
        user.setUsername("username");
        
        user.setPassword("");
        assertFalse(user.isValid());
        user.setPassword(null);
        assertFalse(user.isValid());
        user.setPassword("password");
        
        user.setName("");
        assertFalse(user.isValid());
        user.setName(null);
        assertFalse(user.isValid());
        user.setName("Name");
        
        user.setSignInCount(-1);
        assertFalse(user.isValid());
        user.setSignInCount(0);
        
        user.setCurrentSignInAt(null);
        assertFalse(user.isValid());
        user.setCurrentSignInAt(LocalDateTime.now().plusDays(1));
        assertFalse(user.isValid());
        user.setCurrentSignInAt(LocalDateTime.now());

        user.setLastSignInAt(null);
        assertFalse(user.isValid());
        user.setLastSignInAt(LocalDateTime.now().plusDays(1));
        assertFalse(user.isValid());
        user.setLastSignInAt(LocalDateTime.now());

        user.setCurrentSignInIp("");
        assertFalse(user.isValid());
        user.setCurrentSignInIp(null);
        assertFalse(user.isValid());
        user.setCurrentSignInIp("127.0.0.1");

        user.setLastSignInIp("");
        assertFalse(user.isValid());
        user.setLastSignInIp(null);
        assertFalse(user.isValid());
        user.setLastSignInIp("127.0.0.1");
        
        assertTrue(user.isValid());
    }
    
	@Test
	public void map() {
		User user = user();
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		assertEqualsDTO(user, userDTO);
		
		User mappedUser = new User();
		
		modelMapper.map(userDTO, mappedUser);
		
		assertEquals(user.getId(), mappedUser.getId());
		assertEquals(user.getUsername(), mappedUser.getUsername());
		assertEquals(user.getName(), mappedUser.getName());
		assertNull(mappedUser.getPassword());
		assertEquals(0, mappedUser.getSignInCount());
		assertNull(mappedUser.getCurrentSignInAt());
		assertNull(mappedUser.getLastSignInAt());
		assertNull(mappedUser.getCurrentSignInIp());
		assertNull(mappedUser.getLastSignInIp());
		assertTrue(mappedUser.getAuthorities().isEmpty());		
	}
    
}
