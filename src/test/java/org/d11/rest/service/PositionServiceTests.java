package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.positions;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PositionDTO;
import org.d11.rest.model.jpa.Position;
import org.d11.rest.repository.PositionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PositionServiceTests extends RepositoryServiceTests<Position, PositionDTO, PositionRepository, PositionService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new PositionService(mock(PositionRepository.class)));
		List<Position> positions = positions();
		setD11RestEntities(positions);
		
		super.beforeAll();
	}	
	
}
