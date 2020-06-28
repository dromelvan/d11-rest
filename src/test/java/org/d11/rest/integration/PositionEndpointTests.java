package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.positions;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PositionDTO;
import org.d11.rest.controller.PositionController;
import org.d11.rest.model.jpa.Position;
import org.d11.rest.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PositionEndpointTests extends RepositoryIntegrationTests<Position, PositionDTO, PositionController> {

	@Autowired
	public PositionEndpointTests(PositionRepository positionRepository) {
		super(positionRepository);
	}
	
	@Override
	protected PositionDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<PositionDTO>() {});
	}
	
	@Override
	protected List<PositionDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<PositionDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		List<Position> positions = positions(5);

		PositionRepository positionRepository = getRepository(PositionRepository.class);
		positionRepository.saveAll(positions);
		
		setD11RestEntities(positionRepository.findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.POSITIONS);
	}
	
	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.POSITIONS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.POSITION);
	}
		
}
