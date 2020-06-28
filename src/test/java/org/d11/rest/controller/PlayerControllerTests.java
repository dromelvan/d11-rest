package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.country;
import static org.d11.rest.model.D11RestMock.players;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerDTO;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.service.PlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerControllerTests extends RepositoryControllerTests<Player, PlayerDTO, PlayerController> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new PlayerController(mock(PlayerService.class)));

		List<Player> players = players();
		players.forEach(player -> player.setCountry(country()));
		setD11RestEntities(players);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<PlayerDTO> getDTOClass() {
		return PlayerDTO.class;
	}
	
}
