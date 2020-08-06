package org.d11.rest.service;

import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

import org.d11.rest.api.model.*;
import org.d11.rest.api.util.Parameterizer;
import org.d11.rest.model.jpa.projection.PlayerSearchResult;
import org.d11.rest.repository.PlayerRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService extends D11RestService {

    private Pattern exactSearchPattern = Pattern.compile("\"(.*)\"");
    private PlayerRepository playerRepository;
    private final static Logger logger = LoggerFactory.getLogger(SearchService.class);
    
    @Autowired
    public SearchService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
    public SearchResultDTO search(String search) {
        List<PlayerSearchResult> players = null;
        
        Matcher exactPatternMatcher = this.exactSearchPattern.matcher(search);        
        if(exactPatternMatcher.matches()) {
            search = Parameterizer.parameterize(search);            
            logger.trace("Performing search with search string {}.", search);
            players = this.playerRepository.findByParameterizedName(search);
        } else {
            StringBuilder stringBuilder = new StringBuilder("%");
            for(String string : search.split("[ +]")) {
                stringBuilder.append(Parameterizer.parameterize(string));
                stringBuilder.append("%");
            }        
            logger.trace("Performing search with search string {}.", stringBuilder);
            players = this.playerRepository.findByParameterizedNameLike(stringBuilder.toString());
        }
        
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.getPlayers().addAll(players.stream().map(player -> map(player, PlayerSearchResultDTO.class)).collect(Collectors.toList()));        
        return searchResultDTO;
    }
}
