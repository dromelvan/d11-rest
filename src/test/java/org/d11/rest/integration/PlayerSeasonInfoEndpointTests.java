package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.controller.PlayerSeasonInfoController;
import org.d11.rest.model.jpa.PlayerSeasonInfo;
import org.d11.rest.repository.PlayerSeasonInfoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerSeasonInfoEndpointTests extends SeasonMockEndpointTests<PlayerSeasonInfo, PlayerSeasonInfoDTO, PlayerSeasonInfoController> {

    @Override
    protected PlayerSeasonInfoDTO readValue(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<PlayerSeasonInfoDTO>() {});
    }
    
    @Override
    protected List<PlayerSeasonInfoDTO> readValues(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<List<PlayerSeasonInfoDTO>>() {});
    }
    
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        setD11RestEntities(getRepository(PlayerSeasonInfoRepository.class).findAll());
    }
    
    @Test
    public void findAll() throws Exception {
        super.findAll(Endpoint.PLAYER_SEASON_INFOS);
    }
    
    @Test
    public void findAllIds() throws Exception {
        super.findAllIds(Endpoint.PLAYER_SEASON_INFOS_IDS);
    }
    
    @Test
    public void findById() throws Exception {
        super.findById(Endpoint.PLAYER_SEASON_INFO);
    }
    
}
