package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonStats;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.model.jpa.PlayerSeasonStat;
import org.d11.rest.repository.PlayerSeasonStatRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerSeasonStatServiceTests extends RepositoryServiceTests<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatRepository, PlayerSeasonStatService> {

    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new PlayerSeasonStatService(mock(PlayerSeasonStatRepository.class)));
        List<PlayerSeasonStat> playerSeasonStats = playerSeasonStats();

        for(PlayerSeasonStat playerSeasonStat : playerSeasonStats) {
            playerSeasonStat.setPlayer(player());
            playerSeasonStat.setSeason(season());
        }
        
        setD11RestEntities(playerSeasonStats);
        
        super.beforeAll();
    }   
    
}
