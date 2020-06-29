package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.MatchDTO;
import org.d11.rest.controller.MatchController;
import org.d11.rest.model.jpa.Match;
import org.d11.rest.repository.MatchRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class MatchEndpointTests extends SeasonMockEndpointTests<Match, MatchDTO, MatchController> {

	@Override
	protected MatchDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<MatchDTO>() {});
	}
	
	@Override
	protected List<MatchDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<MatchDTO>>() {});
	}

	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		setD11RestEntities(getRepository(MatchRepository.class).findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.MATCHES);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.MATCHES_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.MATCH);
	}

	@Test
	public void findMatchEventsById() throws Exception {
		super.findById(Endpoint.MATCH_EVENTS);
	}
	
}
