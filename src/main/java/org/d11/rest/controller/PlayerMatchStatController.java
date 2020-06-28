package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.*;
import org.d11.rest.service.PlayerMatchStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerMatchStatController extends RepositoryController<PlayerMatchStatBaseDTO, PlayerMatchStatService> {

    @Autowired
    public PlayerMatchStatController(PlayerMatchStatService playerMatchStatService) {
        super(playerMatchStatService);
    }

    @GetMapping(Endpoint.PLAYER_MATCH_STATS)
    public ResponseEntity<List<PlayerMatchStatBaseDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.PLAYER_MATCH_STATS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.PLAYER_MATCH_STAT)
    public ResponseEntity<PlayerMatchStatBaseDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.PLAYER_MATCH_STAT_BY_D11_MATCH_ID)
    public ResponseEntity<PlayerMatchStatsByD11TeamIdPositionDTO> findByD11MatchId(@PathVariable("id") long id) {
        PlayerMatchStatsByD11TeamIdPositionDTO playerMatchStatsByD11TeamIdPositionDTO = getRepositoryService().findByD11MatchId(id);
        return ResponseEntity.ok(playerMatchStatsByD11TeamIdPositionDTO);
    }
    
}
