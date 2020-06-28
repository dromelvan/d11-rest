package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerMatchStat;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerMatchStatDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class PlayerMatchStatTests extends PlayerStatTests {

	@Test
	public void isValid() {
		PlayerMatchStat playerMatchStat = playerMatchStat();
		Player player = player();
		playerMatchStat.setPlayer(player);
		Match match = match();
		match.addPlayerMatchStat(playerMatchStat);
		Team team = team();
		playerMatchStat.setTeam(team);
		D11Team d11Team = d11Team();
		playerMatchStat.setD11Team(d11Team);
		Position position = position();
		playerMatchStat.setPosition(position);
		
		assertTrue(playerMatchStat.isValid());
		
		isValid(playerMatchStat);
		
		playerMatchStat.setPlayer(null);
		assertFalse(match.isValid());
		playerMatchStat.setPlayer(player);
		
		match.removePlayerMatchStat(playerMatchStat);
		assertFalse(match.isValid());
		match.addPlayerMatchStat(playerMatchStat);;
		
		playerMatchStat.setTeam(null);
		assertFalse(match.isValid());
		playerMatchStat.setTeam(team);
		
		playerMatchStat.setD11Team(null);
		assertFalse(match.isValid());
		playerMatchStat.setD11Team(d11Team);
		
		playerMatchStat.setPosition(null);
		assertFalse(match.isValid());
		playerMatchStat.setPosition(position);
		
		playerMatchStat.setPlayedPosition(null);
		assertFalse(match.isValid());
		playerMatchStat.setPlayedPosition("");
		assertFalse(match.isValid());
		playerMatchStat.setPlayedPosition("P");
		
		playerMatchStat.setLineup(-1);
		assertFalse(match.isValid());
		playerMatchStat.setLineup(3);
		assertFalse(match.isValid());
		playerMatchStat.setLineup(0);
		
		playerMatchStat.setSubstitutionOnTime(-1);
		assertFalse(match.isValid());
		playerMatchStat.setSubstitutionOnTime(91);
		assertFalse(match.isValid());
		playerMatchStat.setSubstitutionOnTime(0);
		
		playerMatchStat.setSubstitutionOffTime(-1);
		assertFalse(match.isValid());
		playerMatchStat.setSubstitutionOffTime(91);
		assertFalse(match.isValid());
		playerMatchStat.setSubstitutionOffTime(0);

		playerMatchStat.setYellowCardTime(-1);
		assertFalse(match.isValid());
		playerMatchStat.setYellowCardTime(91);
		assertFalse(match.isValid());
		playerMatchStat.setYellowCardTime(0);

		playerMatchStat.setRedCardTime(-1);
		assertFalse(match.isValid());
		playerMatchStat.setRedCardTime(91);
		assertFalse(match.isValid());
		playerMatchStat.setRedCardTime(0);

		playerMatchStat.setManOfTheMatch(null);
		assertFalse(match.isValid());
		playerMatchStat.setManOfTheMatch(false);

		playerMatchStat.setSharedManOfTheMatch(null);
		assertFalse(match.isValid());
		playerMatchStat.setSharedManOfTheMatch(false);
		
		assertTrue(playerMatchStat.isValid());		
	}	
	
	@Test
	public void map() {
		PlayerMatchStat playerMatchStat = playerMatchStat();
		Player player = player();
		playerMatchStat.setPlayer(player);
		Match match = match();
		match.addPlayerMatchStat(playerMatchStat);
		Team team = team();
		playerMatchStat.setTeam(team);
		playerMatchStat.getMatch().setHomeTeam(team);
		playerMatchStat.getMatch().setAwayTeam(team());
		D11Team d11Team = d11Team();
		playerMatchStat.setD11Team(d11Team);
		Position position = position();
		playerMatchStat.setPosition(position);

		ModelMapper modelMapper = new D11RestModelMapper();
		
		PlayerMatchStatDTO playerMatchStatDTO = modelMapper.map(playerMatchStat, PlayerMatchStatDTO.class);
		assertEqualsDTO(playerMatchStat, playerMatchStatDTO);
		
		PlayerMatchStat mappedPlayerMatchStat = new PlayerMatchStat();
		
		modelMapper.map(playerMatchStatDTO, mappedPlayerMatchStat);
		assertEquals(playerMatchStat.getId(), mappedPlayerMatchStat.getId());
		assertEquals(playerMatchStat.getPlayedPosition(), mappedPlayerMatchStat.getPlayedPosition());		
		assertEquals(playerMatchStat.getLineup(), mappedPlayerMatchStat.getLineup());		
		assertEquals(playerMatchStat.getSubstitutionOnTime(), mappedPlayerMatchStat.getSubstitutionOnTime());
		assertEquals(playerMatchStat.getSubstitutionOffTime(), mappedPlayerMatchStat.getSubstitutionOffTime());
		assertEquals(playerMatchStat.getYellowCardTime(), mappedPlayerMatchStat.getYellowCardTime());
		assertEquals(playerMatchStat.getRedCardTime(), mappedPlayerMatchStat.getRedCardTime());
		assertEquals(playerMatchStat.isManOfTheMatch(), mappedPlayerMatchStat.isManOfTheMatch());
		assertEquals(playerMatchStat.isSharedManOfTheMatch(), mappedPlayerMatchStat.isSharedManOfTheMatch());		
		assertEquals(playerMatchStat.getGoals(), mappedPlayerMatchStat.getGoals());
		assertEquals(playerMatchStat.getGoalAssists(), mappedPlayerMatchStat.getGoalAssists());
		assertEquals(playerMatchStat.getOwnGoals(), mappedPlayerMatchStat.getOwnGoals());
		assertEquals(playerMatchStat.getGoalsConceded(), mappedPlayerMatchStat.getGoalsConceded());
		assertEquals(playerMatchStat.getRating(), mappedPlayerMatchStat.getRating());
		assertEquals(playerMatchStat.getPoints(), mappedPlayerMatchStat.getPoints());
		assertNull(mappedPlayerMatchStat.getPlayer());
		assertNull(mappedPlayerMatchStat.getMatch());
		assertNull(mappedPlayerMatchStat.getTeam());
		assertNull(mappedPlayerMatchStat.getD11Team());
		assertNull(mappedPlayerMatchStat.getPosition());		
	}
	
}
