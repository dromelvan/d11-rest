package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerMatchStatDTO;
import org.d11.rest.controller.PlayerMatchStatController;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.repository.PlayerMatchStatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerMatchStatEndpointTests extends SeasonMockEndpointTests<PlayerMatchStat, PlayerMatchStatDTO, PlayerMatchStatController> {

	@Override
	protected PlayerMatchStatDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<PlayerMatchStatDTO>() {});
	}
	
	@Override
	protected List<PlayerMatchStatDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<PlayerMatchStatDTO>>() {});
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
	
}
