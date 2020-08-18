package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.collection.*;
import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.PlayerMatchStatRepository;
import org.d11.rest.util.PlayerMatchStats;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerMatchStatServiceTests extends RepositoryServiceTests<PlayerMatchStat, PlayerMatchStatBaseDTO, PlayerMatchStatRepository, PlayerMatchStatService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new PlayerMatchStatService(mock(PlayerMatchStatRepository.class)));
		List<PlayerMatchStat> playerMatchStats = playerMatchStats();
		for(PlayerMatchStat playerMatchStat : playerMatchStats) {
			Player player = player();
			playerMatchStat.setPlayer(player);
			Match match = match();
            match.setMatchDay(matchDay());
            match.setStadium(stadium());			
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
    public void findByMatchId() {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats(getD11RestEntities());
        when(getRepositoryService().getJpaRepository().findByMatchId((long)1)).thenReturn(playerMatchStats);
        when(getRepositoryService().getJpaRepository().findByMatchId((long)-1)).thenReturn(new PlayerMatchStats());
                
        MatchPlayerMatchStatsDTO result = getRepositoryService().findByMatchId((long)1);
        assertEqualsDTO(playerMatchStats, result);
                
        result = getRepositoryService().findByMatchId((long)-1);
        assertTrue(result.isEmpty());
    }
	
    @Test
    public void findByD11MatchId() {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats(getD11RestEntities());
        when(getRepositoryService().getJpaRepository().findByD11MatchId((long)1)).thenReturn(playerMatchStats);
        when(getRepositoryService().getJpaRepository().findByD11MatchId((long)-1)).thenReturn(new PlayerMatchStats());
                
        D11MatchPlayerMatchStatsDTO result = getRepositoryService().findByD11MatchId((long)1);
        assertEqualsDTO(playerMatchStats, result);
                
        result = getRepositoryService().findByD11MatchId((long)-1);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findByPlayerIdAndSeasonId() {
        List<PlayerMatchStat> playerMatchStats = new ArrayList<PlayerMatchStat>(getD11RestEntities());
        when(getRepositoryService().getJpaRepository().findByPlayerIdAndMatchMatchDayPremierLeagueSeasonId((long)1, (long)1)).thenReturn(playerMatchStats);
        when(getRepositoryService().getJpaRepository().findByPlayerIdAndMatchMatchDayPremierLeagueSeasonId((long)-1, (long)-1)).thenReturn(new ArrayList<PlayerMatchStat>());
                
        List<PlayerMatchStatDTO> result = getRepositoryService().findByPlayerIdAndSeasonId((long)1, (long)1);
        assertEqualsDTO(playerMatchStats, result);
                
        result = getRepositoryService().findByPlayerIdAndSeasonId((long)-1, (long)-1);
        assertTrue(result.isEmpty());
    }
    
}
