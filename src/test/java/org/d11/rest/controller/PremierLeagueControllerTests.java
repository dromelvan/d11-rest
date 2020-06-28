package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.premierLeagues;
import static org.d11.rest.model.D11RestMock.seasons;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PremierLeagueDTO;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.service.PremierLeagueService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PremierLeagueControllerTests extends RepositoryControllerTests<PremierLeague, PremierLeagueDTO, PremierLeagueController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new PremierLeagueController(mock(PremierLeagueService.class)));
		
		List<PremierLeague> premierLeagues = premierLeagues();
		List<Season> seasons = seasons(premierLeagues.size());
		for(int i = 0; i < premierLeagues.size(); ++i) {
			premierLeagues.get(i).setSeason(seasons.get(i));
		}
		setD11RestEntities(premierLeagues);
		
		super.beforeAll();
		
		when(getRepositoryController().getRepositoryService().findCurrentPremierLeague()).thenReturn(map(getD11RestEntities().get(0)));
	}
	
	@Test
	public void findCurrentPremierLeague() {
		PremierLeague premierLeague = getD11RestEntities().get(0);
		
		ResponseEntity<PremierLeagueDTO> responseEntity = getRepositoryController().findCurrentPremierLeague();
		PremierLeagueDTO premierLeagueDTO = responseEntity.getBody();
		
		assertEqualsDTO(premierLeague, premierLeagueDTO);
	}
	
	@Override
	protected Class<PremierLeagueDTO> getDTOClass() {
		return PremierLeagueDTO.class;
	}
	
}
