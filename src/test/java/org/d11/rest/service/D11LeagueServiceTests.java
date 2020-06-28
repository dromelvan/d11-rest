package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Leagues;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11LeagueDTO;
import org.d11.rest.model.jpa.D11League;
import org.d11.rest.repository.D11LeagueRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11LeagueServiceTests extends RepositoryServiceTests<D11League, D11LeagueDTO, D11LeagueRepository, D11LeagueService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new D11LeagueService(mock(D11LeagueRepository.class)));
		List<D11League> d11Leagues = d11Leagues();
		d11Leagues.forEach(d11League -> d11League.setSeason(season()));
		setD11RestEntities(d11Leagues);
		
		super.beforeAll();
		when(getRepositoryService().getJpaRepository().findByOrderBySeasonDateDesc()).thenReturn(getD11RestEntities());
		when(getRepositoryService().getJpaRepository().findFirstByOrderBySeasonDateDesc()).thenReturn(Optional.of(getD11RestEntities().get(0)));
	}
	
	@Test
	public void findCurrentD11League() {
		D11LeagueDTO d11LeagueDTO = getRepositoryService().findCurrentD11League();
		assertEqualsDTO(getD11RestEntities().get(0), d11LeagueDTO);
	}
	
}
