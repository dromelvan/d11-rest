package org.d11.rest.controller;

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

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.model.jpa.PlayerSeasonInfo;
import org.d11.rest.service.PlayerSeasonInfoService;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerSeasonInfoControllerTests extends RepositoryControllerTests<PlayerSeasonInfo, PlayerSeasonInfoDTO, PlayerSeasonInfoController> {

    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new PlayerSeasonInfoController(mock(PlayerSeasonInfoService.class)));

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
    
    @Override
    protected Class<PlayerSeasonInfoDTO> getDTOClass() {
        return PlayerSeasonInfoDTO.class;
    }
    
    @Test
    public void findByPlayerIdAndSeasonId() {
        PlayerSeasonInfo playerSeasonInfo = getD11RestEntities().get(0);
        when(getRepositoryController().getRepositoryService().findByPlayerIdAndSeasonId((long)1, (long)1)).thenReturn(map(playerSeasonInfo));
        when(getRepositoryController().getRepositoryService().findByPlayerIdAndSeasonId((long)0, (long)0)).thenThrow(new NotFoundException());
        
        ResponseEntity<PlayerSeasonInfoDTO> responseEntity = getRepositoryController().findByPlayerIdAndSeasonId(1, 1);
        PlayerSeasonInfoDTO playerSeasonInfoDTO = responseEntity.getBody();
        
        assertEqualsDTO(playerSeasonInfo, playerSeasonInfoDTO);
        
        assertThrows(NotFoundException.class, () -> getRepositoryController().findByPlayerIdAndSeasonId(0, 0));
    }
    
}
