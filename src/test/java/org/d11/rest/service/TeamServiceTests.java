package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.teams;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.TeamDTO;
import org.d11.rest.model.jpa.Team;
import org.d11.rest.repository.TeamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class TeamServiceTests extends RepositoryServiceTests<Team, TeamDTO, TeamRepository, TeamService> {
	
	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new TeamService(mock(TeamRepository.class)));
		List<Team> teams = teams();
		teams.forEach(team -> team.setStadium(stadium()));
		setD11RestEntities(teams);
		
		super.beforeAll();
		when(getRepositoryService().getJpaRepository().findByOrderByNameAsc()).thenReturn(getD11RestEntities());
	}	
	
}
