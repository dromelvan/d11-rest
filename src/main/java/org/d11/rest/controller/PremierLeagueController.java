package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PremierLeagueDTO;
import org.d11.rest.service.PremierLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PremierLeagueController extends RepositoryController<PremierLeagueDTO, PremierLeagueService> {

    @Autowired
    public PremierLeagueController(PremierLeagueService premierLeagueService) {
        super(premierLeagueService);
    }

    @GetMapping(Endpoint.PREMIER_LEAGUES)
    public ResponseEntity<List<PremierLeagueDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.PREMIER_LEAGUES_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.PREMIER_LEAGUE)
    public ResponseEntity<PremierLeagueDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.PREMIER_LEAGUE_CURRENT)
    public ResponseEntity<PremierLeagueDTO> findCurrentPremierLeague() {
        PremierLeagueDTO premierLeagueDTO = getRepositoryService().findCurrentPremierLeague();
        return ResponseEntity.ok(premierLeagueDTO);
    }

}
