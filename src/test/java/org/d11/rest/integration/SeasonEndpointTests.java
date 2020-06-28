package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.SeasonDTO;
import org.d11.rest.controller.SeasonController;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class SeasonEndpointTests extends SeasonMockEndpointTests<Season, SeasonDTO, SeasonController> {
		
	@Override
	protected SeasonDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<SeasonDTO>() {});
	}
	
	@Override
	protected List<SeasonDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<SeasonDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		setD11RestEntities(getRepository(SeasonRepository.class).findByOrderByDateDesc());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.SEASONS);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.SEASONS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.SEASON);
	}
	
	@Test
	public void findCurrentSeason() throws Exception {
		assertOk(get(Endpoint.SEASON_CURRENT));
		assertOk(get(Endpoint.SEASON_CURRENT), token(user()));
		
		MvcResult mvcResult = assertOk(get(Endpoint.SEASON_CURRENT), token(administrator()));
		SeasonDTO seasonDTO = readValue(mvcResult);
		
		assertEqualsDTO(getD11RestEntities().get(0), seasonDTO);
	}
	
}
