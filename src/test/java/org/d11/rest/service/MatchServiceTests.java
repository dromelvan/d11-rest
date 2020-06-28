package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.matches;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.MatchDTO;
import org.d11.rest.api.model.MatchMatchEventsDTO;
import org.d11.rest.api.model.MatchMatchStatsDTO;
import org.d11.rest.model.jpa.Match;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.repository.MatchRepository;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class MatchServiceTests extends RepositoryServiceTests<Match, MatchDTO, MatchRepository, MatchService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new MatchService(mock(MatchRepository.class)));
		
		PremierLeague premierLeague = premierLeague();
		List<Match> matches = matches();
		MatchDay matchDay = matchDay();
	    premierLeague.addMatchDay(matchDay);
		
		matches.forEach(match -> match.setHomeTeam(team()));
		matches.forEach(match -> match.setAwayTeam(team()));
		matches.forEach(match -> match.setMatchDay(matchDay));
		matches.forEach(match -> match.setStadium(stadium()));
		setD11RestEntities(matches);
		
		super.beforeAll();
	}

	@Test
	public void findMatchEventsById() {
		Match match = getD11RestEntities().get(0);
		MatchMatchEventsDTO result = getRepositoryService().findMatchEventsById(match.getId());
		
		assertNotNull(result);
		assertEqualsDTO(match, result);
		
		assertThrows(NotFoundException.class, () -> getRepositoryService().findMatchEventsById((long)-1));						
	}

	@Test
	public void findMatchStatsById() {
		Match match = getD11RestEntities().get(0);
		MatchMatchStatsDTO result = getRepositoryService().findMatchStatsById(match.getId());
		
		assertNotNull(result);
		assertEqualsDTO(match, result);
		
		assertThrows(NotFoundException.class, () -> getRepositoryService().findMatchStatsById((long)-1));						
	}
	
}
