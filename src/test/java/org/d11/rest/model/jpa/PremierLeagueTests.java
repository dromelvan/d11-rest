package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDays;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.season;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PremierLeagueDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class PremierLeagueTests {

	@Test
	public void isValid() {
		Season season = season();
		PremierLeague premierLeague = premierLeague();
		season.setPremierLeague(premierLeague);
				
		assertTrue(premierLeague.isValid());
		
		premierLeague.setName(null);
		assertFalse(premierLeague.isValid());
		premierLeague.setName("");
		assertFalse(premierLeague.isValid());
		premierLeague.setName("Name");
		
		season.setPremierLeague(null);
		assertFalse(premierLeague.isValid());
		
		season.setPremierLeague(premierLeague);
		assertTrue(premierLeague.isValid());
	}

	@Test
	public void references() {
		PremierLeague premierLeague = premierLeague();
		List<MatchDay> matchDays = matchDays();
		
		matchDays.forEach(matchDay -> premierLeague.addMatchDay(matchDay));
		matchDays.forEach(matchDay -> assertEquals(premierLeague, matchDay.getPremierLeague()));
		
		matchDays.forEach(matchDay -> premierLeague.removeMatchDay(matchDay));
		matchDays.forEach(matchDay -> assertEquals(null, matchDay.getPremierLeague()));
	}
		
	@Test
	public void map() {
		PremierLeague premierLeague = premierLeague();
		premierLeague.setSeason(season());
		matchDays().forEach(matchDay -> premierLeague.addMatchDay(matchDay));
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		PremierLeagueDTO premierLeagueDTO = modelMapper.map(premierLeague, PremierLeagueDTO.class);
		
		assertEqualsDTO(premierLeague, premierLeagueDTO);
		
		PremierLeague mappedPremierLeague = new PremierLeague();
		
		modelMapper.map(premierLeagueDTO, mappedPremierLeague);
		
		assertEquals(premierLeague.getId(), mappedPremierLeague.getId());
		assertEquals(premierLeague.getName(), mappedPremierLeague.getName());
		assertNull(mappedPremierLeague.getSeason());
		assertNotNull(mappedPremierLeague.getMatchDays());
		assertTrue(mappedPremierLeague.getMatchDays().isEmpty());
	}
	
}
