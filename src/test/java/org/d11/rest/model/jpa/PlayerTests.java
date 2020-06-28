package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.country;
//import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.player;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class PlayerTests {

	@Test
	public void isValid() {
		Player player = player();
		Country country = country();
		player.setCountry(country);
		player.prePersist();
		
		assertTrue(player.isValid());
		
		player.setCountry(null);
		assertFalse(player.isValid());
		player.setCountry(country);
		
		player.setWhoScoredId(null);
		assertFalse(player.isValid());
		player.setWhoScoredId(0);
		assertFalse(player.isValid());
		player.setWhoScoredId(1);
		
		player.setFirstName(null);
		assertFalse(player.isValid());
		player.setFirstName("");
		assertTrue(player.isValid());
		player.setFirstName("Name");

		player.setLastName(null);
		assertFalse(player.isValid());
		player.setLastName("");
		assertFalse(player.isValid());
		player.setLastName("Name");
	
		assertEquals((player.getFirstName() + " " + player.getLastName()).trim(), player.getName());
		assertEquals((player.getLastName() + " " + player.getFirstName().charAt(0)).trim(), player.getShortName());
		player.setFirstName("");
		assertEquals((player.getLastName()).trim(), player.getName());
		assertEquals((player.getLastName()).trim(), player.getShortName());
		player.setFirstName("Name");
		
		player.setDateOfBirth(null);
		assertFalse(player.isValid());
		player.setDateOfBirth(LocalDate.now());
		
		player.setHeight(null);
		assertFalse(player.isValid());
		player.setHeight(-1);
		assertFalse(player.isValid());
		player.setHeight(1);
		
		player.setWeight(null);
		assertFalse(player.isValid());
		player.setWeight(-1);
		assertFalse(player.isValid());
		player.setWeight(1);
		
		player.setParameterizedName(null);
		assertFalse(player.isValid());
		player.setParameterizedName("");
		assertFalse(player.isValid());
		
		player.setFirstName("First");
		player.setLastName("Last");
		player.prePersist();
		assertEquals(player.getParameterizedName(), "first-last");
		
		player.setFirstName("");
		player.setLastName("Jordão");
		player.prePersist();
		assertEquals(player.getParameterizedName(), "jordao");
		
		player.setFirstName("Bonds");
		player.setLastName("N'Gala");
		player.prePersist();
		assertEquals(player.getParameterizedName(), "bonds-n-gala");

		player.setFirstName("Daniel");
		player.setLastName("Tözer");
		player.prePersist();
		assertEquals(player.getParameterizedName(), "daniel-tozer");

		player.setFirstName("Shaun");
		player.setLastName("Wright-Phillips");
		player.prePersist();
		assertEquals(player.getParameterizedName(), "shaun-wright-phillips");
		
	}
	
	@Test
	public void map() {
		Player player = player();
		Country country = country();
		player.setCountry(country);
		player.prePersist();

		ModelMapper modelMapper = new D11RestModelMapper();
		
		PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);
		
		assertEqualsDTO(player, playerDTO);
		
		Player mappedPlayer = new Player();
		
		modelMapper.map(playerDTO, mappedPlayer);
		
		assertEquals(player.getId(), mappedPlayer.getId());
		assertEquals(player.getWhoScoredId(), mappedPlayer.getWhoScoredId());
		assertEquals(player.getFirstName(), mappedPlayer.getFirstName());
		assertEquals(player.getLastName(), mappedPlayer.getLastName());
		assertEquals(player.getName(), mappedPlayer.getName());
		assertEquals(player.getShortName(), mappedPlayer.getShortName());
		assertEquals(player.getDateOfBirth(), mappedPlayer.getDateOfBirth());
		assertEquals(player.getHeight(), mappedPlayer.getHeight());
		assertEquals(player.getWeight(), mappedPlayer.getWeight());
		assertEquals(player.getParameterizedName(), mappedPlayer.getParameterizedName());		
		assertNull(mappedPlayer.getCountry());
	}
	
}
