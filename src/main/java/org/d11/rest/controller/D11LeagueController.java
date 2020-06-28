package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11LeagueDTO;
import org.d11.rest.service.D11LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class D11LeagueController extends RepositoryController<D11LeagueDTO, D11LeagueService> {

    @Autowired
    public D11LeagueController(D11LeagueService d11LeagueService) {
        super(d11LeagueService);
    }

    @GetMapping(Endpoint.D11_LEAGUES)
    public ResponseEntity<List<D11LeagueDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.D11_LEAGUES_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.D11_LEAGUE)
    public ResponseEntity<D11LeagueDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.D11_LEAGUE_CURRENT)
    public ResponseEntity<D11LeagueDTO> findCurrentD11League() {
        D11LeagueDTO d11LeagueDTO = getRepositoryService().findCurrentD11League();
        return ResponseEntity.ok(d11LeagueDTO);
    }

}
