package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.d11Teams;
import static org.d11.rest.model.D11RestMock.user;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11TeamDTO;
import org.d11.rest.controller.D11TeamController;
import org.d11.rest.model.jpa.D11Team;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.D11TeamRepository;
import org.d11.rest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class D11TeamEndpointTests extends RepositoryIntegrationTests<D11Team, D11TeamDTO, D11TeamController> {

	@Autowired
	public D11TeamEndpointTests(D11TeamRepository d11TeamRepository, UserRepository userRepository) {
		super(d11TeamRepository, userRepository);
	}

	@Override
	protected D11TeamDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<D11TeamDTO>() {});
	}
	
	@Override
	protected List<D11TeamDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<D11TeamDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		UserRepository userRepository = getRepository(UserRepository.class);
		
		List<D11Team> d11Teams = d11Teams();
		for(D11Team d11Team : d11Teams) {
			User owner = user();
			owner.setUsername(d11Team.getName() + "owner");
			d11Team.setOwner(userRepository.save(owner));
		}

		D11TeamRepository d11TeamRepository = getRepository(D11TeamRepository.class);
		d11TeamRepository.saveAll(d11Teams);
		
		setD11RestEntities(getRepository(D11TeamRepository.class).findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.D11_TEAMS);
	}
	
	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.D11_TEAMS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.D11_TEAM);
	}
	
}
