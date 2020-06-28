package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.seasons;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.SeasonDTO;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.service.SeasonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class SeasonControllerTests extends RepositoryControllerTests<Season, SeasonDTO, SeasonController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new SeasonController(mock(SeasonService.class)));
		
		List<Season> seasons = seasons();
		seasons.forEach(season -> season.setPremierLeague(premierLeague()));
		seasons.forEach(season -> season.setD11League(d11League()));
		setD11RestEntities(seasons);
		
		super.beforeAll();
		
		when(getRepositoryController().getRepositoryService().findCurrentSeason()).thenReturn(map(getD11RestEntities().get(0)));
	}
					
	@Test
	public void findCurrentSeason() {
		Season season = getD11RestEntities().get(0);
		
		ResponseEntity<SeasonDTO> responseEntity = getRepositoryController().findCurrentSeason();
		SeasonDTO seasonDTO = responseEntity.getBody();
		
		assertEqualsDTO(season, seasonDTO);
	}
	
	@Override
	protected Class<SeasonDTO> getDTOClass() {
		return SeasonDTO.class;
	}
	
}
