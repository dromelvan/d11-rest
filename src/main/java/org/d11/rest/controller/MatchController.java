package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.MatchDTO;
import org.d11.rest.api.model.MatchMatchEventsDTO;
import org.d11.rest.api.model.MatchMatchStatsDTO;
import org.d11.rest.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MatchController extends RepositoryController<MatchDTO, MatchService> {

    @Autowired
    public MatchController(MatchService matchService) {
        super(matchService);
    }

    @GetMapping(Endpoint.MATCHES)
    public ResponseEntity<List<MatchDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.MATCHES_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.MATCH)
    public ResponseEntity<MatchDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.MATCH_EVENTS)
    public ResponseEntity<MatchMatchEventsDTO> findMatchEventsById(@PathVariable("id") long id) {
        MatchMatchEventsDTO matchMatchEventsDTO = getRepositoryService().findMatchEventsById(id);
        return ResponseEntity.ok(matchMatchEventsDTO);
    }

    @GetMapping(Endpoint.MATCH_STATS)
    public ResponseEntity<MatchMatchStatsDTO> findMatchStatsById(@PathVariable("id") long id) {
        MatchMatchStatsDTO matchMatchStatsDTO = getRepositoryService().findMatchStatsById(id);
        return ResponseEntity.ok(matchMatchStatsDTO);
    }

}
