package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.service.PlayerSeasonStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerSeasonStatController extends RepositoryController<PlayerSeasonStatDTO, PlayerSeasonStatService> {

    @Autowired
    public PlayerSeasonStatController(PlayerSeasonStatService playerSeasonStatService) {
        super(playerSeasonStatService);
    }

    @GetMapping(Endpoint.PLAYER_SEASON_STATS)
    public ResponseEntity<List<PlayerSeasonStatDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.PLAYER_SEASON_STATS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.PLAYER_SEASON_STAT)
    public ResponseEntity<PlayerSeasonStatDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }
    
}
