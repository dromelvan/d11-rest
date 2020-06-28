package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.cards;
import static org.d11.rest.model.D11RestMock.goals;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.substitutions;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.MatchDTO;
import org.d11.rest.api.model.MatchMatchEventsDTO;
import org.d11.rest.api.model.Status;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class MatchTests {

	@Test
	public void isValid() {
		Match match = match();
		Team homeTeam = team(1);
		Team awayTeam = team(2);
		MatchDay matchDay = matchDay();
		Stadium stadium = stadium();
		
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		matchDay.addMatch(match);
		match.setStadium(stadium);
		
		assertTrue(match.isValid());
		
		match.setHomeTeam(null);
		assertFalse(match.isValid());
		match.setHomeTeam(homeTeam);

		match.setAwayTeam(null);
		assertFalse(match.isValid());
		match.setAwayTeam(awayTeam);
		
		matchDay.removeMatch(match);
		assertFalse(match.isValid());
		matchDay.addMatch(match);
		
		match.setStadium(null);
		assertFalse(match.isValid());
		match.setStadium(stadium);
		
		match.setWhoScoredId(0);
		assertFalse(match.isValid());
		match.setWhoScoredId(1);
		
		match.setHomeTeamGoals(-1);
		assertFalse(match.isValid());
		match.setHomeTeamGoals(0);
		
		match.setAwayTeamGoals(-1);
		assertFalse(match.isValid());
		match.setAwayTeamGoals(0);

		match.setPreviousHomeTeamGoals(-1);
		assertFalse(match.isValid());
		match.setPreviousHomeTeamGoals(0);
		
		match.setPreviousAwayTeamGoals(-1);
		assertFalse(match.isValid());
		match.setPreviousAwayTeamGoals(0);
		
		match.setDatetime(null);
		assertFalse(match.isValid());
		match.setDatetime(LocalDateTime.now());
		
		match.setElapsed(null);
		assertFalse(match.isValid());
		match.setElapsed("NA");
		
		match.setStatus(null);
		assertFalse(match.isValid());
		match.setStatus(Status.PENDING);

		assertTrue(match.isValid());
	}

	@Test
	public void references() {
		Match match = match();
		List<PlayerMatchStat> playerMatchStats = playerMatchStats();
		
		playerMatchStats.forEach(playerMatchStat -> match.addPlayerMatchStat(playerMatchStat));
		playerMatchStats.forEach(playerMatchStat -> assertEquals(match, playerMatchStat.getMatch()));

		playerMatchStats.forEach(playerMatchStat -> match.removePlayerMatchStat(playerMatchStat));
		playerMatchStats.forEach(playerMatchStat -> assertEquals(null, playerMatchStat.getMatch()));
		
		List<Goal> goals = goals();
		
		goals.forEach(goal -> match.addGoal(goal));
		goals.forEach(goal -> assertEquals(match, goal.getMatch()));

		goals.forEach(goal -> match.removeGoal(goal));
		goals.forEach(goal -> assertEquals(null, goal.getMatch()));

		List<Card> cards = cards();
		
		cards.forEach(card -> match.addCard(card));
		cards.forEach(card -> assertEquals(match, card.getMatch()));

		cards.forEach(card -> match.removeCard(card));
		cards.forEach(card -> assertEquals(null, card.getMatch()));
		
		List<Substitution> substitutions = substitutions();
		
		substitutions.forEach(substitution -> match.addSubstitution(substitution));
		substitutions.forEach(substitution -> assertEquals(match, substitution.getMatch()));

		substitutions.forEach(substitution -> match.removeSubstitution(substitution));
		substitutions.forEach(substitution -> assertEquals(null, substitution.getMatch()));		
	}
	
	@Test
	public void map() {
		Match match = match();
		match.setHomeTeamGoals(1);
		match.setAwayTeamGoals(2);
		match.setPreviousHomeTeamGoals(0);
		match.setPreviousAwayTeamGoals(1);		
		Team homeTeam = team(1);
		Team awayTeam = team(2);
		List<Goal> goals = goals(4);
		for(Goal goal : goals) {
			match.addGoal(goal);
			goal.setPlayer(player());
			goal.setTeam(goal.getId() <= 2 ? homeTeam : awayTeam);
		}
		List<Card> cards = cards(4);
		for(Card card : cards) {
			match.addCard(card);
			card.setPlayer(player());
			card.setTeam(card.getId() <= 2 ? homeTeam : awayTeam);
		}		
		List<Substitution> substitutions = substitutions(4);
		for(Substitution substitution : substitutions) {
			match.addSubstitution(substitution);
			substitution.setPlayer(player());
			substitution.setPlayerIn(player());
			substitution.setTeam(substitution.getId() <= 2 ? homeTeam : awayTeam);
		}				
		PremierLeague premierLeague = premierLeague();
		MatchDay matchDay = matchDay();
		premierLeague.addMatchDay(matchDay);

		Stadium stadium = stadium();
		
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		matchDay.addMatch(match);
		match.setStadium(stadium);

		ModelMapper modelMapper = new D11RestModelMapper();
		
		MatchDTO matchDTO = modelMapper.map(match, MatchDTO.class);
		assertEqualsDTO(match, matchDTO);
		
		MatchMatchEventsDTO matchDetailsDTO = modelMapper.map(match, MatchMatchEventsDTO.class);
		assertEqualsDTO(match, matchDetailsDTO);
		
		Match mappedMatch = new Match();
		
		modelMapper.map(matchDTO, mappedMatch);
		
		assertEquals(match.getId(), mappedMatch.getId());
		assertEquals(match.getWhoScoredId(), mappedMatch.getWhoScoredId());		
		assertEquals(match.getHomeTeamGoals(), mappedMatch.getHomeTeamGoals());
		assertEquals(match.getAwayTeamGoals(), mappedMatch.getAwayTeamGoals());
		assertEquals(match.getPreviousHomeTeamGoals(), mappedMatch.getPreviousHomeTeamGoals());
		assertEquals(match.getPreviousAwayTeamGoals(), mappedMatch.getPreviousAwayTeamGoals());
		assertEquals(match.getDatetime(), mappedMatch.getDatetime());
		assertEquals(match.getElapsed(), mappedMatch.getElapsed());		
		assertEquals(match.getStatus(), mappedMatch.getStatus());
		assertNull(mappedMatch.getHomeTeam());
		assertNull(mappedMatch.getAwayTeam());
		assertNull(mappedMatch.getMatchDay());
		assertNull(mappedMatch.getStadium());
	}
	
}
