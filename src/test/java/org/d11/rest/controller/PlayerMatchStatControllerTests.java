package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.collection.*;
import org.d11.rest.api.model.PlayerMatchStatBaseDTO;
import org.d11.rest.model.jpa.*;
import org.d11.rest.service.PlayerMatchStatService;
import org.d11.rest.service.mapper.*;
import org.d11.rest.util.PlayerMatchStats;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerMatchStatControllerTests extends RepositoryControllerTests<PlayerMatchStat, PlayerMatchStatBaseDTO, PlayerMatchStatController> {

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
	protected Class<PlayerMatchStatBaseDTO> getDTOClass() {
		return PlayerMatchStatBaseDTO.class;
	}

    @Test
    public void findByMatchId() {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats(getD11RestEntities());
        when(getRepositoryController().getRepositoryService().findByMatchId((long)1)).thenReturn(new MatchPlayerMatchStatsConverter(new D11RestModelMapper()).convert(playerMatchStats));
        when(getRepositoryController().getRepositoryService().findByMatchId((long)-1)).thenReturn(new MatchPlayerMatchStatsDTO());
                
        ResponseEntity<MatchPlayerMatchStatsDTO> response = getRepositoryController().findByMatchId((long)1);
        MatchPlayerMatchStatsDTO result = response.getBody();
        assertEqualsDTO(playerMatchStats, result);
                
        response = getRepositoryController().findByMatchId((long)-1);
        result = response.getBody();
        assertTrue(result.isEmpty());
    }
	
    @Test
    public void findByD11MatchId() {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats(getD11RestEntities());
        when(getRepositoryController().getRepositoryService().findByD11MatchId((long)1)).thenReturn(new D11MatchPlayerMatchStatsConverter(new D11RestModelMapper()).convert(playerMatchStats));
        when(getRepositoryController().getRepositoryService().findByD11MatchId((long)-1)).thenReturn(new D11MatchPlayerMatchStatsDTO());
                
        ResponseEntity<D11MatchPlayerMatchStatsDTO> response = getRepositoryController().findByD11MatchId((long)1);
        D11MatchPlayerMatchStatsDTO result = response.getBody();
        assertEqualsDTO(playerMatchStats, result);
                
        response = getRepositoryController().findByD11MatchId((long)-1);
        result = response.getBody();
        assertTrue(result.isEmpty());
    }
	
}
