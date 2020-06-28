package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PremierLeagueDTO;
import org.d11.rest.controller.PremierLeagueController;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.repository.PremierLeagueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PremierLeagueEndpointTests extends SeasonMockEndpointTests<PremierLeague, PremierLeagueDTO, PremierLeagueController> {
	
	@Override
	protected PremierLeagueDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<PremierLeagueDTO>() {});
	}
	
	@Override
	protected List<PremierLeagueDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<PremierLeagueDTO>>() {});
	}
	
	@BeforeEach	
	public void beforeEach() {
		super.beforeEach();
		setD11RestEntities(getRepository(PremierLeagueRepository.class).findByOrderBySeasonDateDesc());		
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.PREMIER_LEAGUES);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.PREMIER_LEAGUES_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.PREMIER_LEAGUE);
	}
	
	@Test
	public void findCurrentPremierLeague() throws Exception {
		assertOk(get(Endpoint.PREMIER_LEAGUE_CURRENT));
		assertOk(get(Endpoint.PREMIER_LEAGUE_CURRENT), token(user()));
		
		MvcResult mvcResult = assertOk(get(Endpoint.PREMIER_LEAGUE_CURRENT), token(administrator()));
		PremierLeagueDTO premierLeagueDTO = readValue(mvcResult);
		
		assertEqualsDTO(getD11RestEntities().get(0), premierLeagueDTO);
	}
	
}
