package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.teams;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.TeamDTO;
import org.d11.rest.model.jpa.Team;
import org.d11.rest.service.TeamService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class TeamControllerTests extends RepositoryControllerTests<Team, TeamDTO, TeamController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new TeamController(mock(TeamService.class)));
		
		List<Team> teams = teams();
		teams.forEach(team -> team.setStadium(stadium()));
		
		setD11RestEntities(teams);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<TeamDTO> getDTOClass() {
		return TeamDTO.class;
	}
	
}
