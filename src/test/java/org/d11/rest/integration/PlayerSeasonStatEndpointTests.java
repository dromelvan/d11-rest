package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.controller.PlayerSeasonStatController;
import org.d11.rest.model.jpa.PlayerSeasonStat;
import org.d11.rest.repository.PlayerSeasonStatRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerSeasonStatEndpointTests extends SeasonMockEndpointTests<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatController> {

    @Override
    protected PlayerSeasonStatDTO readValue(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<PlayerSeasonStatDTO>() {});
    }
    
    @Override
    protected List<PlayerSeasonStatDTO> readValues(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<List<PlayerSeasonStatDTO>>() {});
    }
    
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        setD11RestEntities(getRepository(PlayerSeasonStatRepository.class).findAll());
    }
    
    @Test
    public void findAll() throws Exception {
        super.findAll(Endpoint.PLAYER_SEASON_STATS);
    }
    
    @Test
    public void findAllIds() throws Exception {
        super.findAllIds(Endpoint.PLAYER_SEASON_STATS_IDS);
    }
    
    @Test
    public void findById() throws Exception {
        super.findById(Endpoint.PLAYER_SEASON_STAT);
    }
    
}
