package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.country;
import static org.d11.rest.model.D11RestMock.players;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerDTO;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerServiceTests extends RepositoryServiceTests<Player, PlayerDTO, PlayerRepository, PlayerService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new PlayerService(mock(PlayerRepository.class)));
		List<Player> players = players();
		players.forEach(player -> player.setCountry(country()));
		setD11RestEntities(players);
		
		super.beforeAll();
	}	
	
}
