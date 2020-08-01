package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.players;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.model.SearchResultDTO;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.repository.PlayerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class SearchServiceTests {
    
    private List<Player> players;
    private SearchService searchService;
    
    @BeforeAll
    public void beforeAll() {
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        this.players = players(2);
        this.players.get(0).setFirstName("FirstA");
        this.players.get(0).setLastName("LastA");
        this.players.get(1).setFirstName("FirstB");
        this.players.get(1).setLastName("LastB");
        
        players.forEach(player -> {
            List<Player> result = new ArrayList<Player>();
            player.prePersist();
            result.add(player);  
            when(playerRepository.findByParameterizedNameLike("%" + player.getParameterizedName().replace("-", "%") + "%")).thenReturn(result);            
        });
        this.searchService = new SearchService(playerRepository);
    }
    
    @Test
    public void search() {
        for(Player player : this.players) {
            SearchResultDTO searchResultDTO = this.searchService.search(player.getName());
            assertEquals(1, searchResultDTO.getPlayers().size());
            assertEqualsDTO(player, searchResultDTO.getPlayers().get(0));
        }
    }
}
