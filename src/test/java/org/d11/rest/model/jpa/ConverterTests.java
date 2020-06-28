package org.d11.rest.model.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.model.jpa.converter.RoleAuthorityConverter;
import org.d11.rest.security.Role;
import org.d11.rest.security.RoleAuthority;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.UNIT_TEST)
public class ConverterTests {

	@Test
	public void roleAuthorityConverter() {
		RoleAuthorityConverter roleAuthorityConverter = new RoleAuthorityConverter();
		RoleAuthority userRoleAuthority = new RoleAuthority(Role.USER);
		RoleAuthority administratorRoleAuthority = new RoleAuthority(Role.ADMINISTRATOR);
		
		List<RoleAuthority> roleAuthorities = roleAuthorityConverter.convertToEntityAttribute(false);
		
		assertEquals(1, roleAuthorities.size());
		assertTrue(roleAuthorities.contains(userRoleAuthority));
		assertFalse(roleAuthorities.contains(administratorRoleAuthority));
		
		boolean administrator = roleAuthorityConverter.convertToDatabaseColumn(roleAuthorities);
		assertFalse(administrator);
		
		roleAuthorities = roleAuthorityConverter.convertToEntityAttribute(true);
		
		assertEquals(2, roleAuthorities.size());
		assertTrue(roleAuthorities.contains(userRoleAuthority));
		assertTrue(roleAuthorities.contains(administratorRoleAuthority));
		
		administrator = roleAuthorityConverter.convertToDatabaseColumn(roleAuthorities);
		assertTrue(administrator);
	}
	
}
