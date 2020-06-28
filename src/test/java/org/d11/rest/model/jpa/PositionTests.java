package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.position;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PositionDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class PositionTests {

	@Test
	public void isValid() {
		Position position = position();
		
		assertTrue(position.isValid());
		
		position.setName(null);
		assertFalse(position.isValid());
		position.setName("");
		assertFalse(position.isValid());		
		position.setName("Position");

		position.setCode(null);
		assertFalse(position.isValid());
		position.setCode("");
		assertFalse(position.isValid());		
		position.setCode("PPP");
		assertFalse(position.isValid());				
		position.setCode("P");

		position.setDefender(null);
		assertFalse(position.isValid());
		position.setDefender(false);
		
		position.setSortOrder(null);
		assertFalse(position.isValid());
		position.setSortOrder(-1);
		assertFalse(position.isValid());		
		position.setSortOrder(1);
		
		assertTrue(position.isValid());
	}
	
	@Test
	public void map() {
		Position position = position();

		ModelMapper modelMapper = new D11RestModelMapper();
		
		PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
		
		assertEqualsDTO(position, positionDTO);
		
		Position mappedPosition = new Position();
		
		modelMapper.map(positionDTO, mappedPosition);
		
		assertEquals(position.getId(), mappedPosition.getId());
		assertEquals(position.getName(), mappedPosition.getName());
		assertEquals(position.getCode(), mappedPosition.getCode());
		assertEquals(position.getSortOrder(), mappedPosition.getSortOrder());		
	}
	
}
