package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.GoalDTO;
import org.d11.rest.controller.GoalController;
import org.d11.rest.model.jpa.Goal;
import org.d11.rest.repository.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class GoalEndpointTests extends SeasonMockEndpointTests<Goal, GoalDTO, GoalController> {
	
	@Override
	protected GoalDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<GoalDTO>() {});
	}
	
	@Override
	protected List<GoalDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<GoalDTO>>() {});
	}

	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		GoalRepository goalRepository = getRepository(GoalRepository.class);
		setD11RestEntities(goalRepository.findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.GOALS);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.GOALS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.GOAL);
	}
	
}
