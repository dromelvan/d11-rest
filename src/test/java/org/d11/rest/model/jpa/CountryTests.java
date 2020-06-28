package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.country;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.CountryDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class CountryTests {

	@Test
	public void isValid() {
		Country country = country();
		
		assertTrue(country.isValid());
		
		country.setName(null);
		assertFalse(country.isValid());
		country.setName("");
		assertFalse(country.isValid());
		country.setName("Testland");

		country.setIso(null);
		assertFalse(country.isValid());
		country.setIso("");
		assertFalse(country.isValid());
		country.setIso("T");
		assertFalse(country.isValid());
		country.setIso("TLD");
		assertFalse(country.isValid());		
		country.setIso("TL");
		
		assertTrue(country.isValid());
	}
	
	@Test
	public void map() {
		Country country = country();
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		CountryDTO countryDTO = modelMapper.map(country, CountryDTO.class);
		
		assertEqualsDTO(country, countryDTO);
		
		Country mappedCountry = new Country();
		
		modelMapper.map(countryDTO, mappedCountry);
		
		assertEquals(country.getId(), mappedCountry.getId());
		assertEquals(country.getName(), mappedCountry.getName());
		assertEquals(country.getIso(), mappedCountry.getIso());		
	}
	
}
