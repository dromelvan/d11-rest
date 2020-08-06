package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonInfos;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.season;
import static org.d11.rest.model.D11RestMock.team;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.model.jpa.PlayerSeasonInfo;
import org.d11.rest.repository.PlayerSeasonInfoRepository;
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
    
}
