package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.MatchDayDTO;
import org.d11.rest.controller.MatchDayController;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.repository.MatchDayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class MatchDayEndpointTests extends SeasonMockEndpointTests<MatchDay, MatchDayDTO, MatchDayController> {

	@Override
	protected MatchDayDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<MatchDayDTO>() {});
	}
	
	@Override
	protected List<MatchDayDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<MatchDayDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		setD11RestEntities(getRepository(MatchDayRepository.class).findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.MATCH_DAYS);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.MATCH_DAYS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.MATCH_DAY);
	}
	
	@Test
	public void findCurrentMatchDay() throws Exception {
		assertOk(get(Endpoint.MATCH_DAY_CURRENT));
		assertOk(get(Endpoint.MATCH_DAY_CURRENT), token(user()));
		
		MvcResult mvcResult = assertOk(get(Endpoint.MATCH_DAY_CURRENT), token(administrator()));
		MatchDayDTO matchDayDTO = readValue(mvcResult);
		
		assertEqualsDTO(getD11RestEntities().get(0), matchDayDTO);
	}
	
}
