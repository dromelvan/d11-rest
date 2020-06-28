package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Leagues;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11LeagueDTO;
import org.d11.rest.model.jpa.D11League;
import org.d11.rest.service.D11LeagueService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11LeagueControllerTests extends RepositoryControllerTests<D11League, D11LeagueDTO, D11LeagueController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new D11LeagueController(mock(D11LeagueService.class)));
		
		List<D11League> d11Leagues = d11Leagues();
		d11Leagues.forEach(d11League -> d11League.setSeason(season()));
		setD11RestEntities(d11Leagues);
		
		super.beforeAll();
		
		when(getRepositoryController().getRepositoryService().findCurrentD11League()).thenReturn(map(getD11RestEntities().get(0)));
	}
	
	@Test
	public void findCurrentD11League() {
		D11League d11League = getD11RestEntities().get(0);
		
		ResponseEntity<D11LeagueDTO> responseEntity = getRepositoryController().findCurrentD11League();
		D11LeagueDTO d11LeagueDTO = responseEntity.getBody();
		
		assertEqualsDTO(d11League, d11LeagueDTO);
	}
	
	@Override
	protected Class<D11LeagueDTO> getDTOClass() {
		return D11LeagueDTO.class;
	}
	
}