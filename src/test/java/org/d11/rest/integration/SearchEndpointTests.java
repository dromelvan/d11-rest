package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.countries;
import static org.d11.rest.model.D11RestMock.player;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.SearchResultDTO;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TestInstance(Lifecycle.PER_CLASS)
public class SearchEndpointTests extends IntegrationTests {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private PlayerRepository playerRepository;
    private List<Player> players;
    
    @BeforeAll
    public void beforeAll() {        
        getJpaRepositories().add(this.countryRepository);
        getJpaRepositories().add(this.playerRepository);
    }
    
    @BeforeEach
    public void beforeEach() {        
        Random random = new Random(System.currentTimeMillis());
        
        List<Country> countries = countries(10);
        countries.forEach(country -> country.setId(null));
        countries = getRepository(CountryRepository.class).saveAll(countries);
        
        this.players = new ArrayList<Player>();

        String letters = "ABCDEFGHIJ";        
        for(int i = 0; i < 10; ++i) {
            Player player = player();
            player.setId(null);
            player.setFirstName("Player" + letters.charAt(i));
            player.setLastName("Player" + letters.charAt(i));
            player.setCountry(countries.get(random.nextInt(countries.size())));
            players.add(player);
        }
        this.players = getRepository(PlayerRepository.class).saveAll(this.players);        
    }
    
    @Test
    public void search() throws Exception {
        for(Player player : this.players) {
            MvcResult mvcResult = assertOk(get(Endpoint.SEARCH, player.getName().toLowerCase()));
            
            SearchResultDTO searchResultDTO = readValue(mvcResult, SearchResultDTO.class);
            assertNotNull(searchResultDTO);
            assertEquals(1, searchResultDTO.getPlayers().size());
            assertEqualsDTO(player, searchResultDTO.getPlayers().get(0));
        }
        
        MvcResult mvcResult = assertOk(get(Endpoint.SEARCH, "player"));
        SearchResultDTO searchResultDTO = readValue(mvcResult, SearchResultDTO.class);
        assertNotNull(searchResultDTO);
        assertEquals(this.players.size(), searchResultDTO.getPlayers().size());        
    }
    
}
