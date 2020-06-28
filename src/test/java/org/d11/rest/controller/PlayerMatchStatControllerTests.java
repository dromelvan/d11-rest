package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.*;
import org.d11.rest.service.PlayerMatchStatService;
import org.d11.rest.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

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

    @Test
    public void findByD11MatchId() {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats();
        playerMatchStats.addAll(getD11RestEntities());
        when(getRepositoryController().getRepositoryService().findByD11MatchId((long)1)).thenReturn(new PlayerMatchStatsByD11TeamIdPositionConverter(new D11RestModelMapper()).convert(playerMatchStats));
        when(getRepositoryController().getRepositoryService().findByD11MatchId((long)-1)).thenReturn(new PlayerMatchStatsByD11TeamIdPositionDTO());
        
        ResponseEntity<PlayerMatchStatsByD11TeamIdPositionDTO> response = getRepositoryController().findByD11MatchId((long)1);
        
        PlayerMatchStatsByD11TeamIdPositionDTO playerMatchStatsByD11TeamIdPositionDTO = response.getBody();
        
        assertNotNull(playerMatchStatsByD11TeamIdPositionDTO);
        PlayerMatchStatsByD11TeamIdPositionDTO expected = new PlayerMatchStatsByD11TeamIdPositionConverter(new D11RestModelMapper()).convert(playerMatchStats);
        assertEquals(expected.keySet(), playerMatchStatsByD11TeamIdPositionDTO.keySet());
        for(Long d11TeamId : expected.keySet()) {
            assertEquals(expected.get(d11TeamId).keySet(), playerMatchStatsByD11TeamIdPositionDTO.get(d11TeamId).keySet());
            for(String position : expected.get(d11TeamId).keySet()) {
                List<PlayerMatchStatDetailsDTO> expectedPlayerMatchStatDetailsDTO = expected.get(d11TeamId).get(position);
                List<PlayerMatchStatDetailsDTO> resultPlayerMatchStatDetailsDTO = playerMatchStatsByD11TeamIdPositionDTO.get(d11TeamId).get(position);
                for(int i = 0; i < expectedPlayerMatchStatDetailsDTO.size(); ++i) {
                    assertEquals(expectedPlayerMatchStatDetailsDTO.get(i).getId(), resultPlayerMatchStatDetailsDTO.get(i).getId());
                }
            }
        }
        
        playerMatchStatsByD11TeamIdPositionDTO = getRepositoryController().findByD11MatchId((long)-1).getBody();
        assertTrue(playerMatchStatsByD11TeamIdPositionDTO.isEmpty());
    }
	
}
