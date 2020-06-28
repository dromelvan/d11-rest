package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.d11Teams;
import static org.d11.rest.model.D11RestMock.user;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11TeamDTO;
import org.d11.rest.model.jpa.D11Team;
import org.d11.rest.model.jpa.User;
import org.d11.rest.service.D11TeamService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11TeamControllerTests extends RepositoryControllerTests<D11Team, D11TeamDTO, D11TeamController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new D11TeamController(mock(D11TeamService.class)));
		
		List<D11Team> d11Teams = d11Teams();
		for(D11Team d11Team : d11Teams) {
			User owner = user();
			owner.setId(d11Team.getId());
			d11Team.setOwner(owner);
		}
		setD11RestEntities(d11Teams);
		
		super.beforeAll();
	}	
	
	@Override
	protected Class<D11TeamDTO> getDTOClass() {
		return D11TeamDTO.class;
	}
	
}
