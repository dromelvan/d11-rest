package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.country;
import static org.d11.rest.model.D11RestMock.players;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerDTO;
import org.d11.rest.controller.PlayerController;
import org.d11.rest.model.jpa.Country;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.repository.CountryRepository;
import org.d11.rest.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerEndpointTests extends RepositoryIntegrationTests<Player, PlayerDTO, PlayerController> {

	@Autowired
	public PlayerEndpointTests(PlayerRepository playerRepository, CountryRepository countryRepository) {
		super(playerRepository, countryRepository);
	}
	
	@Override
	protected PlayerDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<PlayerDTO>() {});
	}
	
	@Override
	protected List<PlayerDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<PlayerDTO>>() {});
	}
	
	@BeforeEach
	public void beforeEach() {
		Country country = getRepository(CountryRepository.class).save(country());
		
		List<Player> players = players();
		players.forEach(player -> player.setCountry(country));

		PlayerRepository playerRepository = getRepository(PlayerRepository.class);
		playerRepository.saveAll(players);
		
		setD11RestEntities(getRepository(PlayerRepository.class).findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.PLAYERS);
	}
	
	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.PLAYERS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.PLAYER);
	}
	
}
