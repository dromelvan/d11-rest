package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11TeamDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class D11TeamTests {

	@Test
	public void valid() {
		D11Team d11Team = d11Team();
		User owner = user();
		d11Team.setOwner(owner);
		
		assertTrue(d11Team.isValid());
		
		d11Team.setOwner(null);
		assertFalse(d11Team.isValid());
		d11Team.setOwner(owner);

		d11Team.setName(null);
		assertFalse(d11Team.isValid());
		d11Team.setName("");
		assertFalse(d11Team.isValid());
		d11Team.setName("Name");

		d11Team.setShortName(null);
		assertFalse(d11Team.isValid());
		d11Team.setShortName("");
		assertFalse(d11Team.isValid());
		d11Team.setShortName("NM");
		
		d11Team.setCode(null);
		assertFalse(d11Team.isValid());
		d11Team.setCode("");
		assertFalse(d11Team.isValid());
		d11Team.setCode("CODE");
		assertFalse(d11Team.isValid());
		d11Team.setCode("XXX");
		
		d11Team.setDummy(null);
		assertFalse(d11Team.isValid());
		d11Team.setDummy(false);
		
		assertTrue(d11Team.isValid());		
	}
	
	@Test
	public void map() {
		D11Team d11Team = d11Team();
		User owner = user();
		owner.setId((long)1);
		d11Team.setOwner(owner);
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		D11TeamDTO d11TeamDTO = modelMapper.map(d11Team, D11TeamDTO.class);
		
		assertEqualsDTO(d11Team, d11TeamDTO);
		
		D11Team mappedD11Team = new D11Team();
		
		modelMapper.map(d11TeamDTO, mappedD11Team);
		
		assertEquals(d11Team.getId(), mappedD11Team.getId());		
		assertEquals(d11Team.getName(), mappedD11Team.getName());
		assertEquals(d11Team.getShortName(), mappedD11Team.getShortName());
		assertEquals(d11Team.getCode(), mappedD11Team.getCode());
		assertEquals(d11Team.isDummy(), mappedD11Team.isDummy());
		assertNull(mappedD11Team.getOwner());
	}
	
}
