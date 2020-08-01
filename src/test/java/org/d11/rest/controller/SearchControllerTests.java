package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.players;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.service.SearchService;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class SearchControllerTests {

    private List<Player> players;
    private SearchController searchController;
    private ModelMapper modelMapper = new D11RestModelMapper();
    
    @BeforeAll
    public void beforeAll() {
        SearchService searchService = mock(SearchService.class);
        this.players = players(2);
        this.players.get(0).setFirstName("FirstA");
        this.players.get(0).setLastName("LastA");
        this.players.get(1).setFirstName("FirstB");
        this.players.get(1).setLastName("LastB");
        
        players.forEach(player -> {
            SearchResultDTO searchResultDTO = new SearchResultDTO();
            player.prePersist();
            searchResultDTO.getPlayers().add(this.modelMapper.map(player, PlayerBaseDTO.class));  
            when(searchService.search(player.getName())).thenReturn(searchResultDTO);            
        });
        this.searchController = new SearchController(searchService);
    }
     
    @Test
    public void search() {
        for(Player player : this.players) {
            SearchResultDTO searchResultDTO = this.searchController.search(player.getName()).getBody();
            assertNotNull(searchResultDTO);
            assertEquals(1, searchResultDTO.getPlayers().size());
            assertEqualsDTO(player, searchResultDTO.getPlayers().get(0));
        }
    }
    
}
