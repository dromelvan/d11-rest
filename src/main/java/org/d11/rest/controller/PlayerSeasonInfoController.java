package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.service.PlayerSeasonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerSeasonInfoController extends RepositoryController<PlayerSeasonInfoDTO, PlayerSeasonInfoService> {

    @Autowired
    public PlayerSeasonInfoController(PlayerSeasonInfoService playerSeasonInfoService) {
        super(playerSeasonInfoService);
    }

    @GetMapping(Endpoint.PLAYER_SEASON_INFOS)
    public ResponseEntity<List<PlayerSeasonInfoDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.PLAYER_SEASON_INFOS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.PLAYER_SEASON_INFO)
    public ResponseEntity<PlayerSeasonInfoDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.PLAYER_SEASON_INFO_BY_PLAYER_ID_AND_SEASON_ID)
    public ResponseEntity<PlayerSeasonInfoDTO> findByPlayerIdAndSeasonId(@PathVariable("playerId") long playerId, @PathVariable("seasonId") long seasonId) {
        PlayerSeasonInfoDTO playerSeasonInfoDTO = getRepositoryService().findByPlayerIdAndSeasonId(playerId, seasonId);
        return ResponseEntity.ok(playerSeasonInfoDTO);
    }
    
}
