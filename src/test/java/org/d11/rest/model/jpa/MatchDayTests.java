package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.season;
import static org.d11.rest.model.D11RestMock.matches;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.MatchDayDTO;
import org.d11.rest.api.model.Status;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class MatchDayTests {

	@Test
	public void isValid() {
		MatchDay matchDay = matchDay();		
		matchDay.setPremierLeague(premierLeague());
				
		matchDay.setPremierLeague(null);
		assertFalse(matchDay.isValid());
		matchDay.setPremierLeague(premierLeague());
		assertTrue(matchDay.isValid());
		
		matchDay.setDate(null);
		assertFalse(matchDay.isValid());
		matchDay.setDate(LocalDate.now());
		assertTrue(matchDay.isValid());
		
		matchDay.setMatchDayNumber(null);
		assertFalse(matchDay.isValid());
		matchDay.setMatchDayNumber(0);
		assertFalse(matchDay.isValid());
		matchDay.setMatchDayNumber(39);
		assertFalse(matchDay.isValid());
		matchDay.setMatchDayNumber(1);
		assertTrue(matchDay.isValid());

		matchDay.setStatus(null);
		assertFalse(matchDay.isValid());
		matchDay.setStatus(Status.PENDING);
		assertTrue(matchDay.isValid());		
	}

	@Test
	public void references() {
		MatchDay matchDay = matchDay();
		List<Match> matches = matches();
		
		matches.forEach(match -> matchDay.addMatch(match));
		matches.forEach(match -> assertEquals(matchDay, match.getMatchDay()));
		
		matches.forEach(match -> matchDay.removeMatch(match));
		matches.forEach(match -> assertEquals(null, match.getMatchDay()));
	}
	
	@Test
	public void map() {
		Season season = season();
		PremierLeague premierLeague = premierLeague();
		MatchDay matchDay = matchDay();
		season.setPremierLeague(premierLeague);
		premierLeague.addMatchDay(matchDay);
		matchDay.setD11MatchDay(d11MatchDay());
		
		ModelMapper modelMapper = new D11RestModelMapper();
		
		MatchDayDTO matchDayDTO = modelMapper.map(matchDay, MatchDayDTO.class);
		
		assertEqualsDTO(matchDay, matchDayDTO);
		
		MatchDay mappedMatchDay = new MatchDay();
		
		modelMapper.map(matchDayDTO, mappedMatchDay);
		
		assertEquals(matchDay.getId(), mappedMatchDay.getId());
		assertEquals(matchDay.getDate(), mappedMatchDay.getDate());		
		assertEquals(matchDay.getMatchDayNumber(), mappedMatchDay.getMatchDayNumber());
		assertEquals(matchDay.getStatus(), mappedMatchDay.getStatus());		
		assertNull(mappedMatchDay.getPremierLeague());
		assertNotNull(mappedMatchDay.getMatches());
		assertTrue(mappedMatchDay.getMatches().isEmpty());		
	}
	
}
