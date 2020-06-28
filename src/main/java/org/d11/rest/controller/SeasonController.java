package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.SeasonDTO;
import org.d11.rest.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeasonController extends RepositoryController<SeasonDTO, SeasonService> {

    @Autowired
    public SeasonController(SeasonService seasonService) {
        super(seasonService);
    }

    @GetMapping(Endpoint.SEASONS)
    public ResponseEntity<List<SeasonDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.SEASONS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.SEASON)
    public ResponseEntity<SeasonDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.SEASON_CURRENT)
    public ResponseEntity<SeasonDTO> findCurrentSeason() {
        SeasonDTO seasonDTO = getRepositoryService().findCurrentSeason();
        return ResponseEntity.ok(seasonDTO);
    }

}
