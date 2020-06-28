package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PlayerDTO;
import org.d11.rest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayerController extends RepositoryController<PlayerDTO, PlayerService> {

    @Autowired
    public PlayerController(PlayerService playerService) {
        super(playerService);
    }

    @GetMapping(Endpoint.PLAYERS)
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.PLAYERS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.PLAYER)
    public ResponseEntity<PlayerDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
