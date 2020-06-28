package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.team;
import static org.d11.rest.model.D11RestMock.teamTableStat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.TeamTableStatDTO;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class TeamTableStatTests extends TableStatTests {

	@Test
	public void isValid() {
		TeamTableStat teamTableStat = teamTableStat();
		Team team = team();
		teamTableStat.setTeam(team);
		PremierLeague premierLeague = premierLeague();
		teamTableStat.setPremierLeague(premierLeague);
		MatchDay matchDay = matchDay();
		teamTableStat.setMatchDay(matchDay);		

		assertTrue(teamTableStat.isValid());
		
		isValid(teamTableStat);		
		
		teamTableStat.setTeam(null);
		assertFalse(teamTableStat.isValid());				
		teamTableStat.setTeam(team);

		teamTableStat.setPremierLeague(null);
		assertFalse(teamTableStat.isValid());				
		teamTableStat.setPremierLeague(premierLeague);
		
		teamTableStat.setMatchDay(null);
		assertFalse(teamTableStat.isValid());				
		teamTableStat.setMatchDay(matchDay);
		
		assertTrue(teamTableStat.isValid());
	}

	@Test
	public void map() {
		TeamTableStat teamTableStat = teamTableStat();
		Team team = team();
		teamTableStat.setTeam(team);
		PremierLeague premierLeague = premierLeague();
		teamTableStat.setPremierLeague(premierLeague);
		MatchDay matchDay = matchDay();
		teamTableStat.setMatchDay(matchDay);		

		ModelMapper modelMapper = new D11RestModelMapper();
		
		TeamTableStatDTO teamTableStatDTO = modelMapper.map(teamTableStat, TeamTableStatDTO.class);
		assertEqualsDTO(teamTableStat, teamTableStatDTO);
		
		TeamTableStat mappedTeamTableStat = new TeamTableStat();
		
		modelMapper.map(teamTableStatDTO, mappedTeamTableStat);
		
		assertEquals(teamTableStat.getId(), mappedTeamTableStat.getId());
		assertEquals(teamTableStat.getMatchesPlayed(), mappedTeamTableStat.getMatchesPlayed());
		assertEquals(teamTableStat.getMatchesWon(), mappedTeamTableStat.getMatchesWon());
		assertEquals(teamTableStat.getMatchesDrawn(), mappedTeamTableStat.getMatchesDrawn());
		assertEquals(teamTableStat.getMatchesLost(), mappedTeamTableStat.getMatchesLost());
		assertEquals(teamTableStat.getGoalsFor(), mappedTeamTableStat.getGoalsFor());
		assertEquals(teamTableStat.getGoalsAgainst(), mappedTeamTableStat.getGoalsAgainst());
		assertEquals(teamTableStat.getGoalDifference(), mappedTeamTableStat.getGoalDifference());
		assertEquals(teamTableStat.getPoints(), mappedTeamTableStat.getPoints());
		assertEquals(teamTableStat.getRanking(), mappedTeamTableStat.getRanking());
		
		assertEquals(teamTableStat.getHomeMatchesPlayed(), mappedTeamTableStat.getHomeMatchesPlayed());
		assertEquals(teamTableStat.getHomeMatchesWon(), mappedTeamTableStat.getHomeMatchesWon());
		assertEquals(teamTableStat.getHomeMatchesDrawn(), mappedTeamTableStat.getHomeMatchesDrawn());
		assertEquals(teamTableStat.getHomeMatchesLost(), mappedTeamTableStat.getHomeMatchesLost());
		assertEquals(teamTableStat.getHomeGoalsFor(), mappedTeamTableStat.getHomeGoalsFor());
		assertEquals(teamTableStat.getHomeGoalsAgainst(), mappedTeamTableStat.getHomeGoalsAgainst());
		assertEquals(teamTableStat.getHomeGoalDifference(), mappedTeamTableStat.getHomeGoalDifference());
		assertEquals(teamTableStat.getHomePoints(), mappedTeamTableStat.getHomePoints());
		assertEquals(teamTableStat.getHomeRanking(), mappedTeamTableStat.getHomeRanking());
		
		assertEquals(teamTableStat.getAwayMatchesPlayed(), mappedTeamTableStat.getAwayMatchesPlayed());
		assertEquals(teamTableStat.getAwayMatchesWon(), mappedTeamTableStat.getAwayMatchesWon());
		assertEquals(teamTableStat.getAwayMatchesDrawn(), mappedTeamTableStat.getAwayMatchesDrawn());
		assertEquals(teamTableStat.getAwayMatchesLost(), mappedTeamTableStat.getAwayMatchesLost());
		assertEquals(teamTableStat.getAwayGoalsFor(), mappedTeamTableStat.getAwayGoalsFor());
		assertEquals(teamTableStat.getAwayGoalsAgainst(), mappedTeamTableStat.getAwayGoalsAgainst());
		assertEquals(teamTableStat.getAwayGoalDifference(), mappedTeamTableStat.getAwayGoalDifference());
		assertEquals(teamTableStat.getAwayPoints(), mappedTeamTableStat.getAwayPoints());
		assertEquals(teamTableStat.getAwayRanking(), mappedTeamTableStat.getAwayRanking());
		
		assertEquals(teamTableStat.getFormPoints(), mappedTeamTableStat.getFormPoints());
		assertEquals(teamTableStat.getPreviousRanking(), mappedTeamTableStat.getPreviousRanking());
		
		assertNull(mappedTeamTableStat.getTeam());
	}
}
