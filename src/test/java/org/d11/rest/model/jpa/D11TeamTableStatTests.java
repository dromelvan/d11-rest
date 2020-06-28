package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.d11TeamTableStat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11TeamTableStatDTO;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class D11TeamTableStatTests extends TableStatTests {

	@Test
	public void isValid() {
		D11TeamTableStat d11TeamTableStat = d11TeamTableStat();
		D11Team d11Team = d11Team();
		d11TeamTableStat.setD11Team(d11Team);
		D11League d11League = d11League();
		d11TeamTableStat.setD11League(d11League);
		D11MatchDay d11MatchDay = d11MatchDay();
		d11TeamTableStat.setD11MatchDay(d11MatchDay);		

		assertTrue(d11TeamTableStat.isValid());
		
		isValid(d11TeamTableStat);		
		
		d11TeamTableStat.setD11Team(null);
		assertFalse(d11TeamTableStat.isValid());				
		d11TeamTableStat.setD11Team(d11Team);

		d11TeamTableStat.setD11League(null);
		assertFalse(d11TeamTableStat.isValid());				
		d11TeamTableStat.setD11League(d11League);
		
		d11TeamTableStat.setD11MatchDay(null);
		assertFalse(d11TeamTableStat.isValid());				
		d11TeamTableStat.setD11MatchDay(d11MatchDay);
		
		assertTrue(d11TeamTableStat.isValid());
	}

	@Test
	public void map() {
		D11TeamTableStat d11TeamTableStat = d11TeamTableStat();
		D11Team d11Team = d11Team();
		d11TeamTableStat.setD11Team(d11Team);
		D11League d11League = d11League();
        d11TeamTableStat.setD11League(d11League);
		D11MatchDay d11MatchDay = d11MatchDay();
		d11TeamTableStat.setD11MatchDay(d11MatchDay);		

		ModelMapper modelMapper = new D11RestModelMapper();
		
		D11TeamTableStatDTO d11TeamTableStatDTO = modelMapper.map(d11TeamTableStat, D11TeamTableStatDTO.class);
		assertEqualsDTO(d11TeamTableStat, d11TeamTableStatDTO);
		
		D11TeamTableStat mappedD11TeamTableStat = new D11TeamTableStat();
		
		modelMapper.map(d11TeamTableStatDTO, mappedD11TeamTableStat);
		
		assertEquals(d11TeamTableStat.getId(), mappedD11TeamTableStat.getId());
		assertEquals(d11TeamTableStat.getMatchesPlayed(), mappedD11TeamTableStat.getMatchesPlayed());
		assertEquals(d11TeamTableStat.getMatchesWon(), mappedD11TeamTableStat.getMatchesWon());
		assertEquals(d11TeamTableStat.getMatchesDrawn(), mappedD11TeamTableStat.getMatchesDrawn());
		assertEquals(d11TeamTableStat.getMatchesLost(), mappedD11TeamTableStat.getMatchesLost());
		assertEquals(d11TeamTableStat.getGoalsFor(), mappedD11TeamTableStat.getGoalsFor());
		assertEquals(d11TeamTableStat.getGoalsAgainst(), mappedD11TeamTableStat.getGoalsAgainst());
		assertEquals(d11TeamTableStat.getGoalDifference(), mappedD11TeamTableStat.getGoalDifference());
		assertEquals(d11TeamTableStat.getPoints(), mappedD11TeamTableStat.getPoints());
		assertEquals(d11TeamTableStat.getRanking(), mappedD11TeamTableStat.getRanking());
		
		assertEquals(d11TeamTableStat.getHomeMatchesPlayed(), mappedD11TeamTableStat.getHomeMatchesPlayed());
		assertEquals(d11TeamTableStat.getHomeMatchesWon(), mappedD11TeamTableStat.getHomeMatchesWon());
		assertEquals(d11TeamTableStat.getHomeMatchesDrawn(), mappedD11TeamTableStat.getHomeMatchesDrawn());
		assertEquals(d11TeamTableStat.getHomeMatchesLost(), mappedD11TeamTableStat.getHomeMatchesLost());
		assertEquals(d11TeamTableStat.getHomeGoalsFor(), mappedD11TeamTableStat.getHomeGoalsFor());
		assertEquals(d11TeamTableStat.getHomeGoalsAgainst(), mappedD11TeamTableStat.getHomeGoalsAgainst());
		assertEquals(d11TeamTableStat.getHomeGoalDifference(), mappedD11TeamTableStat.getHomeGoalDifference());
		assertEquals(d11TeamTableStat.getHomePoints(), mappedD11TeamTableStat.getHomePoints());
		assertEquals(d11TeamTableStat.getHomeRanking(), mappedD11TeamTableStat.getHomeRanking());
		
		assertEquals(d11TeamTableStat.getAwayMatchesPlayed(), mappedD11TeamTableStat.getAwayMatchesPlayed());
		assertEquals(d11TeamTableStat.getAwayMatchesWon(), mappedD11TeamTableStat.getAwayMatchesWon());
		assertEquals(d11TeamTableStat.getAwayMatchesDrawn(), mappedD11TeamTableStat.getAwayMatchesDrawn());
		assertEquals(d11TeamTableStat.getAwayMatchesLost(), mappedD11TeamTableStat.getAwayMatchesLost());
		assertEquals(d11TeamTableStat.getAwayGoalsFor(), mappedD11TeamTableStat.getAwayGoalsFor());
		assertEquals(d11TeamTableStat.getAwayGoalsAgainst(), mappedD11TeamTableStat.getAwayGoalsAgainst());
		assertEquals(d11TeamTableStat.getAwayGoalDifference(), mappedD11TeamTableStat.getAwayGoalDifference());
		assertEquals(d11TeamTableStat.getAwayPoints(), mappedD11TeamTableStat.getAwayPoints());
		assertEquals(d11TeamTableStat.getAwayRanking(), mappedD11TeamTableStat.getAwayRanking());
		
		assertEquals(d11TeamTableStat.getFormPoints(), mappedD11TeamTableStat.getFormPoints());
		assertEquals(d11TeamTableStat.getPreviousRanking(), mappedD11TeamTableStat.getPreviousRanking());
		
		assertNull(mappedD11TeamTableStat.getD11Team());
	}
}
