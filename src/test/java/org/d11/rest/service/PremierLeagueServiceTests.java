package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.entityId;
import static org.d11.rest.model.D11RestMock.premierLeagues;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PremierLeagueDTO;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.repository.PremierLeagueRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PremierLeagueServiceTests extends RepositoryServiceTests<PremierLeague, PremierLeagueDTO, PremierLeagueRepository, PremierLeagueService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new PremierLeagueService(mock(PremierLeagueRepository.class)));
		List<PremierLeague> premierLeagues = premierLeagues();
		premierLeagues.forEach(premierLeague -> premierLeague.setSeason(season()));
		setD11RestEntities(premierLeagues);
		
		super.beforeAll();
		when(getRepositoryService().getJpaRepository().findByOrderBySeasonDateDesc()).thenReturn(getD11RestEntities());
		when(getRepositoryService().getJpaRepository().findFirstByOrderBySeasonDateDesc()).thenReturn(Optional.of(entityId(getD11RestEntities().get(0).getId())));
	}
	
	@Test
	public void findCurrentPremierLeague() {
		PremierLeagueDTO premierLeagueDTO = getRepositoryService().findCurrentPremierLeague();
		assertEqualsDTO(getD11RestEntities().get(0), premierLeagueDTO);
	}
	
}
