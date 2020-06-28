package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.d11Leagues;
import static org.d11.rest.model.D11RestMock.seasons;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Collections;
import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11LeagueDTO;
import org.d11.rest.controller.D11LeagueController;
import org.d11.rest.model.jpa.D11League;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.repository.D11LeagueRepository;
import org.d11.rest.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class D11LeagueEndpointTests extends RepositoryIntegrationTests<D11League, D11LeagueDTO, D11LeagueController> {

	@Autowired
	public D11LeagueEndpointTests(D11LeagueRepository d11LeagueRepository, SeasonRepository seasonRepository) {
		super(d11LeagueRepository, seasonRepository);
	}
	
	@Override
	protected D11LeagueDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<D11LeagueDTO>() {});
	}
	
	@Override
	protected List<D11LeagueDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<D11LeagueDTO>>() {});
	}
		
	@BeforeEach
	public void beforeEach() {
		List<Season> seasons = seasons();
		Collections.reverse(seasons);
		SeasonRepository seasonRepository = getRepository(SeasonRepository.class);
		seasonRepository.saveAll(seasons);
		seasons = seasonRepository.findByOrderByDateDesc();
		
		List<D11League> d11Leagues = d11Leagues(seasons.size());
		for(int i = 0; i < d11Leagues.size(); ++i) {
			d11Leagues.get(i).setSeason(seasons.get(i));
		}
		
		D11LeagueRepository d11LeagueRepository = getRepository(D11LeagueRepository.class);
	    d11LeagueRepository.saveAll(d11Leagues);
		
		setD11RestEntities(getRepository(D11LeagueRepository.class).findByOrderBySeasonDateDesc());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.D11_LEAGUES);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.D11_LEAGUES_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.D11_LEAGUE);
	}		
	
	@Test
	public void findCurrentD11League() throws Exception {
		assertOk(get(Endpoint.D11_LEAGUE_CURRENT));
		assertOk(get(Endpoint.D11_LEAGUE_CURRENT), token(user()));
		
		MvcResult mvcResult = assertOk(get(Endpoint.D11_LEAGUE_CURRENT), token(administrator()));
		D11LeagueDTO d11LeagueDTO = readValue(mvcResult);
		
		assertEqualsDTO(getD11RestEntities().get(0), d11LeagueDTO);
	}
	
}
