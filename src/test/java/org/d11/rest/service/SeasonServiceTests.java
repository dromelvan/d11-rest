
package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.seasons;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.d11.rest.Tags;
import org.d11.rest.api.model.SeasonDTO;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class SeasonServiceTests extends RepositoryServiceTests<Season, SeasonDTO, SeasonRepository, SeasonService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new SeasonService(mock(SeasonRepository.class)));
		
		List<Season> seasons = seasons();
		seasons.forEach(season -> season.setPremierLeague(premierLeague()));
		seasons.forEach(season -> season.setD11League(d11League()));
		setD11RestEntities(seasons);
		
		super.beforeAll();
		when(getRepositoryService().getJpaRepository().findByOrderByDateDesc()).thenReturn(getD11RestEntities());
		when(getRepositoryService().getJpaRepository().findFirstByOrderByDateDesc()).thenReturn(Optional.of(getD11RestEntities().get(0)));
	}
		
	@Test
	public void findCurrentSeason() {
		SeasonDTO seasonDTO = getRepositoryService().findCurrentSeason();
		assertEqualsDTO(getD11RestEntities().get(0), seasonDTO);
	}
	
}
