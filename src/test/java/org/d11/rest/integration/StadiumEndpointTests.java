package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.stadia;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.StadiumDTO;
import org.d11.rest.controller.StadiumController;
import org.d11.rest.model.jpa.Stadium;
import org.d11.rest.repository.StadiumRepository;
import org.d11.rest.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class StadiumEndpointTests extends RepositoryIntegrationTests<Stadium, StadiumDTO, StadiumController> {

	@Autowired
	public StadiumEndpointTests(StadiumRepository stadiumRepository, TeamRepository teamRepository) {
		super(stadiumRepository, teamRepository);
	}
	
	@Override
	protected StadiumDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<StadiumDTO>() {});
	}
		
	@Override
	protected List<StadiumDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<StadiumDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		List<Stadium> stadia = stadia();
		StadiumRepository stadiumRepository = getRepository(StadiumRepository.class);
		stadia = stadiumRepository.saveAll(stadia);
		
		setD11RestEntities(stadia);
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.STADIA);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.STADIA_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.STADIUM);
	}
	
}
