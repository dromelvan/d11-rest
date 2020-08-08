package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonInfos;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.season;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.model.jpa.PlayerSeasonInfo;
import org.d11.rest.repository.PlayerSeasonInfoRepository;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerSeasonInfoServiceTests extends RepositoryServiceTests<PlayerSeasonInfo, PlayerSeasonInfoDTO, PlayerSeasonInfoRepository, PlayerSeasonInfoService> {

    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new PlayerSeasonInfoService(mock(PlayerSeasonInfoRepository.class)));
        List<PlayerSeasonInfo> playerSeasonInfos = playerSeasonInfos();

        for(PlayerSeasonInfo playerSeasonInfo : playerSeasonInfos) {
            playerSeasonInfo.setPlayer(player());
            playerSeasonInfo.setSeason(season());
            playerSeasonInfo.setTeam(team());
            playerSeasonInfo.setD11Team(d11Team());
            playerSeasonInfo.setPosition(position());
        }
        
        setD11RestEntities(playerSeasonInfos);
        
        super.beforeAll();
    }   
    
    @Test
    public void findByPlayerIdAndSeasonId() {
        PlayerSeasonInfo playerSeasonInfo = getD11RestEntities().get(0);
        when(getRepositoryService().getJpaRepository().findByPlayerIdAndSeasonId((long)1, (long)1)).thenReturn(Optional.of(playerSeasonInfo));
        when(getRepositoryService().getJpaRepository().findByPlayerIdAndSeasonId((long)0, (long)0)).thenReturn(Optional.empty());
        
        PlayerSeasonInfoDTO playerSeasonInfoDTO = getRepositoryService().findByPlayerIdAndSeasonId(1, 1);
        assertEqualsDTO(playerSeasonInfo, playerSeasonInfoDTO);
        
        assertThrows(NotFoundException.class, () -> getRepositoryService().findByPlayerIdAndSeasonId(0, 0));
    }
    
}
