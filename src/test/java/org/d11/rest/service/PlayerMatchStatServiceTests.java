package org.d11.rest.service;

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
import org.d11.rest.repository.PlayerMatchStatRepository;
import org.d11.rest.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerMatchStatServiceTests extends RepositoryServiceTests<PlayerMatchStat, PlayerMatchStatDTO, PlayerMatchStatRepository, PlayerMatchStatService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new PlayerMatchStatService(mock(PlayerMatchStatRepository.class)));
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
	
    @Test
    public void findByD11MatchId() {
        // TODO: Tidy up all the service, controller, endpoint tests for this method.
        PlayerMatchStats playerMatchStats = new PlayerMatchStats();
        playerMatchStats.addAll(getD11RestEntities());
        when(getRepositoryService().getJpaRepository().findByD11MatchId((long)1)).thenReturn(playerMatchStats);
        
        PlayerMatchStatsByD11TeamIdPositionDTO result = getRepositoryService().findByD11MatchId((long)1);
        
        assertNotNull(result);
        PlayerMatchStatsByD11TeamIdPositionDTO expected = new PlayerMatchStatsByD11TeamIdPositionConverter(new D11RestModelMapper()).convert(playerMatchStats);
        assertEquals(expected.keySet(), result.keySet());
        for(Long d11TeamId : expected.keySet()) {
            assertEquals(expected.get(d11TeamId).keySet(), result.get(d11TeamId).keySet());
            for(String position : expected.get(d11TeamId).keySet()) {
                List<PlayerMatchStatDetailsDTO> expectedPlayerMatchStatDetailsDTO = expected.get(d11TeamId).get(position);
                List<PlayerMatchStatDetailsDTO> resultPlayerMatchStatDetailsDTO = result.get(d11TeamId).get(position);
                for(int i = 0; i < expectedPlayerMatchStatDetailsDTO.size(); ++i) {
                    // It's ok to just check id here, the rest is checked in other tests.
                    assertEquals(expectedPlayerMatchStatDetailsDTO.get(i).getId(), resultPlayerMatchStatDetailsDTO.get(i).getId());
                }
            }
        }
        
        result = getRepositoryService().findByD11MatchId((long)-1);
        assertTrue(result.isEmpty());
    }
	
}
