package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.TeamDTO;
import org.d11.rest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeamController extends RepositoryController<TeamDTO, TeamService> {

    @Autowired
    public TeamController(TeamService teamService) {
        super(teamService);
    }

    @GetMapping(Endpoint.TEAMS)
    public ResponseEntity<List<TeamDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.TEAMS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.TEAM)
    public ResponseEntity<TeamDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
