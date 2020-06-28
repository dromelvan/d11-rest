package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11MatchDTO;
import org.d11.rest.controller.D11MatchController;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class D11MatchEndpointTests extends SeasonMockEndpointTests<D11Match, D11MatchDTO, D11MatchController> {

    @Override
    protected D11MatchDTO readValue(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<D11MatchDTO>() {
        });
    }

    @Override
    protected List<D11MatchDTO> readValues(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<List<D11MatchDTO>>() {
        });
    }

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        setD11RestEntities(getRepository(D11MatchRepository.class).findAll());
    }

    @Test
    public void findAll() throws Exception {
        super.findAll(Endpoint.D11_MATCHES);
    }

    @Test
    public void findAllIds() throws Exception {
        super.findAllIds(Endpoint.D11_MATCHES_IDS);
    }

    @Test
    public void findById() throws Exception {
        super.findById(Endpoint.D11_MATCH);

        // Have to built up a model specifically for this to make sure we have a properly set up D11 match
        // to compare with.
        Random random = new Random(System.currentTimeMillis());

        User user = getRepository(UserRepository.class).findAll().get(0);
        MatchDay matchDay = getRepository(MatchDayRepository.class).findAll().get(0);
        Match match = matchDay.getMatches().get(0);
        D11MatchDay d11MatchDay = matchDay.getD11MatchDay();
        D11Match d11Match = d11MatchDay.getD11Matches().get(0);
        List<Player> players = getRepository(PlayerRepository.class).findAll();
        List<Position> positions = getRepository(PositionRepository.class).findAll();

        Map<Long, Long> remainingPlayerCount = new HashMap<>();
        List<PlayerMatchStat> playerMatchStats = playerMatchStats();
        for(PlayerMatchStat playerMatchStat : playerMatchStats) {
            playerMatchStat.setId(null);
            playerMatchStat.setPlayer(players.get(random.nextInt(players.size())));
            match.addPlayerMatchStat(playerMatchStat);
            playerMatchStat.setTeam(random.nextInt(2) == 0 ? match.getHomeTeam() : match.getAwayTeam());
            playerMatchStat.setD11Team(random.nextInt(2) == 0 ? d11Match.getHomeD11Team() : d11Match.getAwayD11Team());
            playerMatchStat.setPosition(positions.get(random.nextInt(positions.size())));

            if(remainingPlayerCount.get(playerMatchStat.getD11Team().getId()) != null) {
                remainingPlayerCount.put(playerMatchStat.getD11Team().getId(), remainingPlayerCount.get(playerMatchStat.getD11Team().getId()) + 1);
            } else {
                remainingPlayerCount.put(playerMatchStat.getD11Team().getId(), (long) 1);
            }
        }
        playerMatchStats = getRepository(PlayerMatchStatRepository.class).saveAll(playerMatchStats);

        MvcResult mvcResult = assertOk(get(Endpoint.D11_MATCH, d11Match.getId()), token(user));

        D11MatchDTO d11MatchDTO = readValue(mvcResult);

        assertThat(d11MatchDTO.getRemainingPlayerCount(), is(remainingPlayerCount));
    }

}
