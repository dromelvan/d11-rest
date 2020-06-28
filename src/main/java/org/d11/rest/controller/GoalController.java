package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.GoalDTO;
import org.d11.rest.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GoalController extends RepositoryController<GoalDTO, GoalService> {

    @Autowired
    public GoalController(GoalService playerService) {
        super(playerService);
    }

    @GetMapping(Endpoint.GOALS)
    public ResponseEntity<List<GoalDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.GOALS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.GOAL)
    public ResponseEntity<GoalDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
