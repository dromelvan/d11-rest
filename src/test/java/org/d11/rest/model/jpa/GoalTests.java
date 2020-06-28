package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.goal;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.GoalDTO;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class GoalTests {

	@Test
	public void isValid() {
		Goal goal = goal();
		Match match = match();
		match.addGoal(goal);
		Team team = team();
		goal.setTeam(team);
		Player player = player();
		goal.setPlayer(player);
		
		assertTrue(goal.isValid());
		
		match.removeGoal(goal);
		assertFalse(goal.isValid());
		match.addGoal(goal);
		
		goal.setTeam(null);
		assertFalse(goal.isValid());
		goal.setTeam(team);
		
		goal.setPlayer(null);
		assertFalse(goal.isValid());
		goal.setPlayer(player);

		goal.setTime(-1);
		assertFalse(goal.isValid());
		goal.setTime(91);
		assertFalse(goal.isValid());
		goal.setTime(45);

		goal.setAddedTime(-1);
		assertFalse(goal.isValid());
		goal.setAddedTime(5);
		
		goal.setPenalty(null);
		assertFalse(goal.isValid());
		goal.setPenalty(false);

		goal.setOwnGoal(null);
		assertFalse(goal.isValid());
		goal.setOwnGoal(false);
		
		goal.setPenalty(true);
		goal.setOwnGoal(true);
		assertFalse(goal.isValid());
		goal.setOwnGoal(false);
		assertTrue(goal.isValid());
	}

	@Test
	public void map() {
		Goal goal = goal();
		Match match = match();
		match.addGoal(goal);
		Team team = team();
		goal.setTeam(team);
		Player player = player();
		goal.setPlayer(player);

		ModelMapper modelMapper = new D11RestModelMapper();
		
		GoalDTO goalDTO = modelMapper.map(goal, GoalDTO.class);
		
		assertEqualsDTO(goal, goalDTO);
		
		Goal mappedGoal = new Goal();
		
		modelMapper.map(goalDTO, mappedGoal);
		
		assertEquals(goal.getId(), mappedGoal.getId());
		assertEquals(goal.getTime(), mappedGoal.getTime());
		assertEquals(goal.getAddedTime(), mappedGoal.getAddedTime());
		assertEquals(goal.isPenalty(), mappedGoal.isPenalty());
		assertEquals(goal.isOwnGoal(), mappedGoal.isOwnGoal());
		assertNull(mappedGoal.getMatch());
		assertNull(mappedGoal.getTeam());
		assertNull(mappedGoal.getPlayer());
	}
	
}
