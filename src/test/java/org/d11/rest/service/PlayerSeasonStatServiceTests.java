package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonStats;
import static org.d11.rest.model.D11RestMock.season;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.model.jpa.PlayerSeasonStat;
import org.d11.rest.repository.PlayerSeasonStatRepository;
import org.d11.rest.util.NotFoundException;
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
    
    @Test
    public void findByPlayerIdAndSeasonId() {
        PlayerSeasonStat playerSeasonStat = getD11RestEntities().get(0);
        when(getRepositoryService().getJpaRepository().findByPlayerIdAndSeasonId((long)1, (long)1)).thenReturn(Optional.of(playerSeasonStat));
        when(getRepositoryService().getJpaRepository().findByPlayerIdAndSeasonId((long)0, (long)0)).thenReturn(Optional.empty());
        
        PlayerSeasonStatDTO playerSeasonStatDTO = getRepositoryService().findByPlayerIdAndSeasonId(1, 1);
        assertEqualsDTO(playerSeasonStat, playerSeasonStatDTO);
        
        assertThrows(NotFoundException.class, () -> getRepositoryService().findByPlayerIdAndSeasonId(0, 0));
    }
    
}
