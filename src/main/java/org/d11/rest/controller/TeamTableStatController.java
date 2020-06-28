package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.TeamTableStatDTO;
import org.d11.rest.service.TeamTableStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeamTableStatController extends RepositoryController<TeamTableStatDTO, TeamTableStatService> {

    @Autowired
    public TeamTableStatController(TeamTableStatService teamTableStatService) {
        super(teamTableStatService);
    }

    @Override
    @GetMapping(Endpoint.TEAM_TABLE_STATS)
    public ResponseEntity<List<TeamTableStatDTO>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping(Endpoint.TEAM_TABLE_STATS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @Override
    @GetMapping(Endpoint.TEAM_TABLE_STAT)
    public ResponseEntity<TeamTableStatDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.TEAM_TABLE_STAT_BY_MATCH_DAY_ID)
    public ResponseEntity<List<TeamTableStatDTO>> findByMatchDayId(@PathVariable("id") long id) {
        List<TeamTableStatDTO> teamTableStats = getRepositoryService().findByMatchDayId(id);
        return ResponseEntity.ok(teamTableStats);
    }

}
