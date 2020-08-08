package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonStats;
import static org.d11.rest.model.D11RestMock.season;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.model.jpa.PlayerSeasonStat;
import org.d11.rest.service.PlayerSeasonStatService;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerSeasonStatControllerTests extends RepositoryControllerTests<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatController> {

    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new PlayerSeasonStatController(mock(PlayerSeasonStatService.class)));

        List<PlayerSeasonStat> playerSeasonStats = playerSeasonStats();

        for(PlayerSeasonStat playerSeasonStat : playerSeasonStats) {
            playerSeasonStat.setPlayer(player());
            playerSeasonStat.setSeason(season());
        }        
        setD11RestEntities(playerSeasonStats);
        
        super.beforeAll();
    }
    
    @Override
    protected Class<PlayerSeasonStatDTO> getDTOClass() {
        return PlayerSeasonStatDTO.class;
    }
    
    @Test
    public void findByPlayerIdAndSeasonId() {
        PlayerSeasonStat playerSeasonStat = getD11RestEntities().get(0);
        when(getRepositoryController().getRepositoryService().findByPlayerIdAndSeasonId((long)1, (long)1)).thenReturn(map(playerSeasonStat));
        when(getRepositoryController().getRepositoryService().findByPlayerIdAndSeasonId((long)0, (long)0)).thenThrow(new NotFoundException());
        
        ResponseEntity<PlayerSeasonStatDTO> responseEntity = getRepositoryController().findByPlayerIdAndSeasonId(1, 1);
        PlayerSeasonStatDTO playerSeasonStatDTO = responseEntity.getBody();
        
        assertEqualsDTO(playerSeasonStat, playerSeasonStatDTO);
        
        assertThrows(NotFoundException.class, () -> getRepositoryController().findByPlayerIdAndSeasonId(0, 0));
    }
    
}
