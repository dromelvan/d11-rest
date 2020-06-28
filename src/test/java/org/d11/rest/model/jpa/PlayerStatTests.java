package org.d11.rest.model.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerStatTests {

	public void isValid(PlayerStat playerStat) {
		playerStat.setGoals(-1);
		assertFalse(playerStat.isValid());
		playerStat.setGoals(0);

		playerStat.setGoalAssists(-1);
		assertFalse(playerStat.isValid());
		playerStat.setGoalAssists(0);

		playerStat.setOwnGoals(-1);
		assertFalse(playerStat.isValid());
		playerStat.setOwnGoals(0);

		playerStat.setGoalsConceded(-1);
		assertFalse(playerStat.isValid());
		playerStat.setGoalsConceded(0);

		playerStat.setRating(-1);
		assertFalse(playerStat.isValid());
		playerStat.setRating(1001);
		assertFalse(playerStat.isValid());		
		playerStat.setRating(0);
		
		playerStat.setPoints(-1);
		assertFalse(playerStat.isValid());
		playerStat.setPoints(0);		
	}
	
}
