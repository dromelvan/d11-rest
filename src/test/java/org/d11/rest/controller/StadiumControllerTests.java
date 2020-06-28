package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.stadia;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.StadiumDTO;
import org.d11.rest.model.jpa.Stadium;
import org.d11.rest.service.StadiumService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class StadiumControllerTests extends RepositoryControllerTests<Stadium, StadiumDTO, StadiumController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new StadiumController(mock(StadiumService.class)));
		
		List<Stadium> stadia = stadia();
		setD11RestEntities(stadia);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<StadiumDTO> getDTOClass() {
		return StadiumDTO.class;
	}
	
}
