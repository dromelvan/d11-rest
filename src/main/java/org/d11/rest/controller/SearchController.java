package org.d11.rest.controller;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.SearchResultDTO;
import org.d11.rest.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController extends D11RestController {

    private SearchService searchService;
    
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    
    @GetMapping(Endpoint.SEARCH)
    public ResponseEntity<SearchResultDTO> search(@PathVariable("search") String search) {
        SearchResultDTO searchResultDTO = this.searchService.search(search);
        return ResponseEntity.ok(searchResultDTO);
    }
    
}
