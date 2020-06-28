package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.TeamDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class TeamTests {

	@Test
	public void isValid() {
		Team team = team();
		Stadium stadium = stadium();
		team.setStadium(stadium);
		
		assertTrue(team.isValid());
		
		team.setStadium(null);
		assertFalse(team.isValid());
		team.setStadium(stadium);
		
		team.setWhoScoredId(null);
		assertFalse(team.isValid());
		team.setWhoScoredId(0);
		assertFalse(team.isValid());
		team.setWhoScoredId(1);
		
		team.setName(null);
		assertFalse(team.isValid());
		team.setName("");
		assertFalse(team.isValid());
		team.setName("Name");

		team.setShortName(null);
		assertFalse(team.isValid());
		team.setShortName("");
		assertFalse(team.isValid());
		team.setShortName("NM");
		
		team.setCode(null);
		assertFalse(team.isValid());
		team.setCode("");
		assertFalse(team.isValid());
		team.setCode("CODE");
		assertFalse(team.isValid());
		team.setCode("XXX");

		team.setNickname(null);
		assertFalse(team.isValid());
		team.setNickname("Nickname");
		
		team.setEstablished(null);
		assertFalse(team.isValid());
		team.setEstablished(1500);
		assertFalse(team.isValid());
		team.setEstablished(2100);
		assertFalse(team.isValid());		
		team.setEstablished(1900);
		
		team.setMotto(null);
		assertFalse(team.isValid());
		team.setMotto("Motto");
		
		team.setColour(null);
		assertFalse(team.isValid());
		team.setColour("");
		assertFalse(team.isValid());
		team.setColour("COLOUR");
		assertFalse(team.isValid());
		team.setColour("#000000");		
		
		team.setDummy(null);
		assertFalse(team.isValid());
		team.setDummy(false);
		
		assertTrue(team.isValid());
	}
	
	@Test
	public void map() {
		Team team = team();
		Stadium stadium = stadium();
		team.setStadium(stadium);
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
		
		assertEqualsDTO(team, teamDTO);
		
		Team mappedTeam = new Team();
		
		modelMapper.map(teamDTO, mappedTeam);
		
		assertEquals(team.getId(), mappedTeam.getId());
		assertEquals(team.getWhoScoredId(), mappedTeam.getWhoScoredId());
		assertEquals(team.getName(), mappedTeam.getName());
		assertEquals(team.getShortName(), mappedTeam.getShortName());
		assertEquals(team.getCode(), mappedTeam.getCode());
		assertEquals(team.getNickname(), mappedTeam.getNickname());
		assertEquals(team.getEstablished(), mappedTeam.getEstablished());
		assertEquals(team.getMotto(), mappedTeam.getMotto());
		assertEquals(team.getColour(), mappedTeam.getColour());
		assertEquals(team.isDummy(), mappedTeam.isDummy());
		assertNull(mappedTeam.getStadium());
	}
	
}
