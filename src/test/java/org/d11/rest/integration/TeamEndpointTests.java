package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.teams;

import java.util.Collections;
import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.TeamDTO;
import org.d11.rest.controller.TeamController;
import org.d11.rest.model.jpa.Stadium;
import org.d11.rest.model.jpa.Team;
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
public class TeamEndpointTests extends RepositoryIntegrationTests<Team, TeamDTO, TeamController> {

	@Autowired
	public TeamEndpointTests(TeamRepository teamRepository, StadiumRepository stadiumRepository) {
		super(teamRepository, stadiumRepository);
	}

	@Override
	protected TeamDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<TeamDTO>() {});
	}
	
	@Override
	protected List<TeamDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<TeamDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		Stadium stadium = getRepository(StadiumRepository.class).save(stadium());
		
		List<Team> teams = teams();
		Collections.reverse(teams);
		teams.forEach(team -> team.setStadium(stadium));

		TeamRepository teamRepository = getRepository(TeamRepository.class);
		teamRepository.saveAll(teams);
		
		setD11RestEntities(getRepository(TeamRepository.class).findByOrderByNameAsc());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.TEAMS);
	}
	
	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.TEAMS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.TEAM);
	}
	
}
