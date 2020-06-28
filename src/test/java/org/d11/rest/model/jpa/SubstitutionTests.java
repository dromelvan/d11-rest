package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.substitution;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.SubstitutionDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class SubstitutionTests {

	@Test
	public void isValid() {
		Substitution substitution = substitution();
		Match match = match();
		match.addSubstitution(substitution);
		Team team = team();
		substitution.setTeam(team);
		Player player = player();
		substitution.setPlayer(player);
		Player playerIn = player(player.getId().intValue() + 1);
		substitution.setPlayerIn(playerIn);
		
		assertTrue(substitution.isValid());
		
		match.removeSubstitution(substitution);
		assertFalse(substitution.isValid());
		match.addSubstitution(substitution);
		
		substitution.setTeam(null);
		assertFalse(substitution.isValid());
		substitution.setTeam(team);
		
		substitution.setPlayer(null);
		assertFalse(substitution.isValid());
		substitution.setPlayer(player);

		substitution.setTime(-1);
		assertFalse(substitution.isValid());
		substitution.setTime(91);
		assertFalse(substitution.isValid());
		substitution.setTime(45);

		substitution.setAddedTime(-1);
		assertFalse(substitution.isValid());
		substitution.setAddedTime(5);
		
		substitution.setPlayerIn(null);
		assertFalse(substitution.isValid());
		substitution.setPlayer(player);
		assertFalse(substitution.isValid());
		substitution.setPlayerIn(playerIn);
	}
	
	@Test
	public void map() {
		Substitution substitution = substitution();
		Match match = match();
		match.addSubstitution(substitution);
		Team team = team();
		substitution.setTeam(team);
		Player player = player();
		substitution.setPlayer(player);
		Player playerIn = player(player.getId().intValue() + 1);
		substitution.setPlayerIn(playerIn);

		ModelMapper modelMapper = new D11RestModelMapper();
		
		SubstitutionDTO substitutionDTO = modelMapper.map(substitution, SubstitutionDTO.class);
		
		assertEqualsDTO(substitution, substitutionDTO);
		
		Substitution mappedSubstitution = new Substitution();
		
		modelMapper.map(substitutionDTO, mappedSubstitution);
		
		assertEquals(substitution.getId(), mappedSubstitution.getId());
		assertEquals(substitution.getTime(), mappedSubstitution.getTime());
		assertEquals(substitution.getAddedTime(), mappedSubstitution.getAddedTime());
		assertNull(mappedSubstitution.getMatch());
		assertNull(mappedSubstitution.getTeam());
		assertNull(mappedSubstitution.getPlayer());
		assertNull(mappedSubstitution.getPlayerIn());
	}
	
}
