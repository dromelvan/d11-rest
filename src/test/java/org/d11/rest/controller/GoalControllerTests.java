package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.goals;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.team;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.GoalDTO;
import org.d11.rest.model.jpa.Goal;
import org.d11.rest.service.GoalService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class GoalControllerTests extends RepositoryControllerTests<Goal, GoalDTO, GoalController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new GoalController(mock(GoalService.class)));

		List<Goal> goals = goals();
		for(Goal goal : goals) {
			match().addGoal(goal);
			goal.setTeam(team());
			goal.setPlayer(player());
		}
		setD11RestEntities(goals);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<GoalDTO> getDTOClass() {
		return GoalDTO.class;
	}
	
}
