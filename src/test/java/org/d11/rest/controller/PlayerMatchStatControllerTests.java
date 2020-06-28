package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.team;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerMatchStatDTO;
import org.d11.rest.model.jpa.D11Team;
import org.d11.rest.model.jpa.Match;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.model.jpa.Position;
import org.d11.rest.model.jpa.Team;
import org.d11.rest.service.PlayerMatchStatService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerMatchStatControllerTests extends RepositoryControllerTests<PlayerMatchStat, PlayerMatchStatDTO, PlayerMatchStatController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new PlayerMatchStatController(mock(PlayerMatchStatService.class)));
		List<PlayerMatchStat> playerMatchStats = playerMatchStats();
		for(PlayerMatchStat playerMatchStat : playerMatchStats) {
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
		}
		
		setD11RestEntities(playerMatchStats);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<PlayerMatchStatDTO> getDTOClass() {
		return PlayerMatchStatDTO.class;
	}

}
