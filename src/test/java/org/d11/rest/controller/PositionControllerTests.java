package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.positions;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PositionDTO;
import org.d11.rest.model.jpa.Position;
import org.d11.rest.service.PositionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PositionControllerTests extends RepositoryControllerTests<Position, PositionDTO, PositionController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new PositionController(mock(PositionService.class)));

		List<Position> positions= positions(5);
		setD11RestEntities(positions);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<PositionDTO> getDTOClass() {
		return PositionDTO.class;
	}
	
}
