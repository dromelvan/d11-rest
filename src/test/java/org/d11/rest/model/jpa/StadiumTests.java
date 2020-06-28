package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.StadiumDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class StadiumTests {

	@Test
	public void isValid() {
		Stadium stadium = stadium();
		
		assertTrue(stadium.isValid());
		
		stadium.setName(null);
		assertFalse(stadium.isValid());
		stadium.setName("");
		assertFalse(stadium.isValid());
		stadium.setName("NAME");
		
		stadium.setCity(null);
		assertFalse(stadium.isValid());
		stadium.setCity("");
		assertFalse(stadium.isValid());
		stadium.setCity("CITY");

		stadium.setCapacity(null);
		assertFalse(stadium.isValid());
		stadium.setCapacity(0);
		assertFalse(stadium.isValid());
		stadium.setCapacity(1);

		stadium.setOpened(null);
		assertFalse(stadium.isValid());
		stadium.setOpened(1500);
		assertFalse(stadium.isValid());
		stadium.setOpened(2100);
		assertFalse(stadium.isValid());		
		stadium.setOpened(1900);
		
		assertTrue(stadium.isValid());
	}
	
	@Test
	public void map() {
		Stadium stadium = stadium();
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		StadiumDTO stadiumDTO = modelMapper.map(stadium, StadiumDTO.class);
		
		assertEqualsDTO(stadium, stadiumDTO);
		
		Stadium mappedStadium = new Stadium();
		
		modelMapper.map(stadiumDTO, mappedStadium);
		
		assertEquals(stadium.getId(), mappedStadium.getId());
		assertEquals(stadium.getName(), mappedStadium.getName());
		assertEquals(stadium.getCity(), mappedStadium.getCity());
		assertEquals(stadium.getCapacity(), mappedStadium.getCapacity());
		assertEquals(stadium.getOpened(), mappedStadium.getOpened());	
	}
	
}
