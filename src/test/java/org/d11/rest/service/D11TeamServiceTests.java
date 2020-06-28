package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.d11Teams;
import static org.d11.rest.model.D11RestMock.user;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11TeamDTO;
import org.d11.rest.model.jpa.D11Team;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.D11TeamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11TeamServiceTests extends RepositoryServiceTests<D11Team, D11TeamDTO, D11TeamRepository, D11TeamService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new D11TeamService(mock(D11TeamRepository.class)));
		List<D11Team> d11Teams = d11Teams();
		for(D11Team d11Team : d11Teams) {
			User owner = user();
			owner.setId(d11Team.getId());
			d11Team.setOwner(owner);
		}
		setD11RestEntities(d11Teams);
		
		super.beforeAll();
	}	
	
}
