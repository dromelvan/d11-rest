package org.d11.rest.model.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TableStatTests {

	public void isValid(TableStat tableStat) {
		tableStat.setMatchesPlayed(null);
		assertFalse(tableStat.isValid());		
		tableStat.setMatchesPlayed(-1);
		assertFalse(tableStat.isValid());
		tableStat.setMatchesPlayed(0);

		tableStat.setMatchesWon(null);
		assertFalse(tableStat.isValid());				
		tableStat.setMatchesWon(-1);
		assertFalse(tableStat.isValid());
		tableStat.setMatchesWon(0);

		tableStat.setMatchesDrawn(null);
		assertFalse(tableStat.isValid());				
		tableStat.setMatchesDrawn(-1);
		assertFalse(tableStat.isValid());
		tableStat.setMatchesDrawn(0);
		
		tableStat.setMatchesLost(null);
		assertFalse(tableStat.isValid());				
		tableStat.setMatchesLost(-1);
		assertFalse(tableStat.isValid());
		tableStat.setMatchesLost(0);

		tableStat.setGoalsFor(null);
		assertFalse(tableStat.isValid());				
		tableStat.setGoalsFor(-1);
		assertFalse(tableStat.isValid());
		tableStat.setGoalsFor(0);

		tableStat.setGoalsAgainst(null);
		assertFalse(tableStat.isValid());				
		tableStat.setGoalsAgainst(-1);
		assertFalse(tableStat.isValid());
		tableStat.setGoalsAgainst(0);

		tableStat.setGoalDifference(null);
		assertFalse(tableStat.isValid());
		tableStat.setGoalDifference(0);

		tableStat.setGoalsAgainst(null);
		assertFalse(tableStat.isValid());				
		tableStat.setGoalsAgainst(-1);
		assertFalse(tableStat.isValid());
		tableStat.setGoalsAgainst(0);

		tableStat.setRanking(null);
		assertFalse(tableStat.isValid());				
		tableStat.setRanking(0);
		assertFalse(tableStat.isValid());
		tableStat.setRanking(1);

		tableStat.setHomeMatchesPlayed(null);
		assertFalse(tableStat.isValid());		
		tableStat.setHomeMatchesPlayed(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeMatchesPlayed(0);

		tableStat.setHomeMatchesWon(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeMatchesWon(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeMatchesWon(0);

		tableStat.setHomeMatchesDrawn(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeMatchesDrawn(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeMatchesDrawn(0);
		
		tableStat.setHomeMatchesLost(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeMatchesLost(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeMatchesLost(0);

		tableStat.setHomeGoalsFor(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeGoalsFor(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeGoalsFor(0);

		tableStat.setHomeGoalsAgainst(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeGoalsAgainst(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeGoalsAgainst(0);

		tableStat.setHomeGoalDifference(null);
		assertFalse(tableStat.isValid());
		tableStat.setHomeGoalDifference(0);

		tableStat.setHomeGoalsAgainst(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeGoalsAgainst(-1);
		assertFalse(tableStat.isValid());
		tableStat.setHomeGoalsAgainst(0);

		tableStat.setHomeRanking(null);
		assertFalse(tableStat.isValid());				
		tableStat.setHomeRanking(0);
		assertFalse(tableStat.isValid());
		tableStat.setHomeRanking(1);
		
		tableStat.setAwayMatchesPlayed(null);
		assertFalse(tableStat.isValid());		
		tableStat.setAwayMatchesPlayed(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayMatchesPlayed(0);

		tableStat.setAwayMatchesWon(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayMatchesWon(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayMatchesWon(0);

		tableStat.setAwayMatchesDrawn(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayMatchesDrawn(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayMatchesDrawn(0);
		
		tableStat.setAwayMatchesLost(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayMatchesLost(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayMatchesLost(0);

		tableStat.setAwayGoalsFor(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayGoalsFor(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayGoalsFor(0);

		tableStat.setAwayGoalsAgainst(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayGoalsAgainst(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayGoalsAgainst(0);

		tableStat.setAwayGoalDifference(null);
		assertFalse(tableStat.isValid());
		tableStat.setAwayGoalDifference(0);

		tableStat.setAwayGoalsAgainst(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayGoalsAgainst(-1);
		assertFalse(tableStat.isValid());
		tableStat.setAwayGoalsAgainst(0);

		tableStat.setAwayRanking(null);
		assertFalse(tableStat.isValid());				
		tableStat.setAwayRanking(0);
		assertFalse(tableStat.isValid());
		tableStat.setAwayRanking(1);

		tableStat.setFormPoints(null);
		assertFalse(tableStat.isValid());				
		tableStat.setFormPoints(-1);
		assertFalse(tableStat.isValid());
		tableStat.setFormPoints(0);

		tableStat.setPreviousRanking(null);
		assertFalse(tableStat.isValid());				
		tableStat.setPreviousRanking(0);
		assertFalse(tableStat.isValid());
		tableStat.setPreviousRanking(1);
	}
	
}
