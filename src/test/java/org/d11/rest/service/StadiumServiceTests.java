package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.stadia;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.StadiumDTO;
import org.d11.rest.model.jpa.Stadium;
import org.d11.rest.repository.StadiumRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class StadiumServiceTests extends RepositoryServiceTests<Stadium, StadiumDTO, StadiumRepository, StadiumService> {
	
	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new StadiumService(mock(StadiumRepository.class)));
		
		List<Stadium> stadia = stadia();
		setD11RestEntities(stadia);
		
		super.beforeAll();		
	}

}
