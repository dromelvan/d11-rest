package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.*;
import org.d11.rest.controller.PlayerMatchStatController;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.*;
import org.d11.rest.service.mapper.*;
import org.d11.rest.util.PlayerMatchStats;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerMatchStatEndpointTests extends SeasonMockEndpointTests<PlayerMatchStat, PlayerMatchStatBaseDTO, PlayerMatchStatController> {

	@Override
	protected PlayerMatchStatBaseDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<PlayerMatchStatBaseDTO>() {});
	}
	
	@Override
	protected List<PlayerMatchStatBaseDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<PlayerMatchStatBaseDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		setD11RestEntities(getRepository(PlayerMatchStatRepository.class).findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.PLAYER_MATCH_STATS);
	}
	
	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.PLAYER_MATCH_STATS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.PLAYER_MATCH_STAT);
	}

    @Test
    public void findByD11MatchId() throws Exception {
        Random random = new Random(System.currentTimeMillis());

        User user = getRepository(UserRepository.class).findAll().get(0);
        MatchDay matchDay = getRepository(MatchDayRepository.class).findAll().get(0);
        Match match = matchDay.getMatches().get(0);
        D11MatchDay d11MatchDay = matchDay.getD11MatchDay();
        D11Match d11Match = d11MatchDay.getD11Matches().get(0);
        List<Player> players = getRepository(PlayerRepository.class).findAll();
        List<Position> positions = getRepository(PositionRepository.class).findAll();

        List<PlayerMatchStat> playerMatchStats = playerMatchStats();
        for(PlayerMatchStat playerMatchStat : playerMatchStats) {
            playerMatchStat.setId(null);
            playerMatchStat.setPlayer(players.get(random.nextInt(players.size())));
            match.addPlayerMatchStat(playerMatchStat);
            playerMatchStat.setTeam(random.nextInt(2) == 0 ? match.getHomeTeam() : match.getAwayTeam());
            playerMatchStat.setD11Team(random.nextInt(2) == 0 ? d11Match.getHomeD11Team() : d11Match.getAwayD11Team());
            playerMatchStat.setPosition(positions.get(random.nextInt(positions.size())));
        }
        playerMatchStats = getRepository(PlayerMatchStatRepository.class).saveAll(playerMatchStats);

        MvcResult mvcResult = assertOk(get(Endpoint.PLAYER_MATCH_STAT_BY_D11_MATCH_ID, d11Match.getId()), token(user));
        PlayerMatchStatsByD11TeamIdPositionDTO playerMatchStatsByD11TeamIdPositionDTO = readValue(mvcResult, new TypeReference<PlayerMatchStatsByD11TeamIdPositionDTO>() {});

        PlayerMatchStats playerMatchStatsObject = new PlayerMatchStats();
        playerMatchStatsObject.addAll(playerMatchStats);        
        PlayerMatchStatsByD11TeamIdPositionDTO expected = new PlayerMatchStatsByD11TeamIdPositionConverter(new D11RestModelMapper()).convert(playerMatchStatsObject);

        assertEquals(expected.keySet(), playerMatchStatsByD11TeamIdPositionDTO.keySet());
        for(Long d11TeamId : expected.keySet()) {
            assertEquals(expected.get(d11TeamId).keySet(), playerMatchStatsByD11TeamIdPositionDTO.get(d11TeamId).keySet());
            for(String position : expected.get(d11TeamId).keySet()) {
                List<PlayerMatchStatDTO> expectedPlayerMatchStatDTO = expected.get(d11TeamId).get(position);
                List<PlayerMatchStatDTO> resultPlayerMatchStatDTO = playerMatchStatsByD11TeamIdPositionDTO.get(d11TeamId).get(position);
                for(int i = 0; i < expectedPlayerMatchStatDTO.size(); ++i) {
                    assertEquals(expectedPlayerMatchStatDTO.get(i).getId(), resultPlayerMatchStatDTO.get(i).getId());
                }
            }
        }    
        
        mvcResult = assertOk(get(Endpoint.PLAYER_MATCH_STAT_BY_D11_MATCH_ID, -1), token(user));
        playerMatchStatsByD11TeamIdPositionDTO = readValue(mvcResult, new TypeReference<PlayerMatchStatsByD11TeamIdPositionDTO>() {});
        assertTrue(playerMatchStatsByD11TeamIdPositionDTO.isEmpty());        
    }
	
}
