package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.matches;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.MatchDTO;
import org.d11.rest.api.model.MatchMatchEventsDTO;
import org.d11.rest.api.model.MatchMatchStatsDTO;
import org.d11.rest.model.jpa.Match;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.service.MatchService;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class MatchControllerTests extends RepositoryControllerTests<Match, MatchDTO, MatchController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new MatchController(mock(MatchService.class)));
		
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
	
	@Override
	protected Class<MatchDTO> getDTOClass() {
		return MatchDTO.class;
	}
	
	@Test
	public void findMatchEventsById() {
		Match match = getD11RestEntities().get(0);		
		when(getRepositoryController().getRepositoryService().findMatchEventsById(match.getId())).thenReturn(map(match, MatchMatchEventsDTO.class));
		when(getRepositoryController().getRepositoryService().findMatchEventsById((long)-1)).thenThrow(NotFoundException.class);		

		ResponseEntity<MatchMatchEventsDTO> responseEntity = getRepositoryController().findMatchEventsById(match.getId());
	
		MatchMatchEventsDTO matchMatchEventsDTO = responseEntity.getBody();
		assertEqualsDTO(match, matchMatchEventsDTO);
		assertThrows(NotFoundException.class, () -> getRepositoryController().findMatchEventsById((long)-1));
	}

	@Test
	public void findMatchStatsById() {
		Match match = getD11RestEntities().get(0);		
		when(getRepositoryController().getRepositoryService().findMatchStatsById(match.getId())).thenReturn(map(match, MatchMatchStatsDTO.class));
		when(getRepositoryController().getRepositoryService().findMatchStatsById((long)-1)).thenThrow(NotFoundException.class);		

		ResponseEntity<MatchMatchStatsDTO> responseEntity = getRepositoryController().findMatchStatsById(match.getId());
	
		MatchMatchStatsDTO matchMatchStatsDTO = responseEntity.getBody();
		assertEqualsDTO(match, matchMatchStatsDTO);
		assertThrows(NotFoundException.class, () -> getRepositoryController().findMatchStatsById((long)-1));
	}
	
}
