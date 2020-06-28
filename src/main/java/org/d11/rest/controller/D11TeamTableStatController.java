package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11TeamTableStatDTO;
import org.d11.rest.service.D11TeamTableStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class D11TeamTableStatController extends RepositoryController<D11TeamTableStatDTO, D11TeamTableStatService> {

    @Autowired
    public D11TeamTableStatController(D11TeamTableStatService d11TeamTableStatService) {
        super(d11TeamTableStatService);
    }

    @Override
    @GetMapping(Endpoint.D11_TEAM_TABLE_STATS)
    public ResponseEntity<List<D11TeamTableStatDTO>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping(Endpoint.D11_TEAM_TABLE_STATS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @Override
    @GetMapping(Endpoint.D11_TEAM_TABLE_STAT)
    public ResponseEntity<D11TeamTableStatDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.D11_TEAM_TABLE_STAT_BY_D11_MATCH_DAY_ID)
    public ResponseEntity<List<D11TeamTableStatDTO>> findByD11MatchDayId(@PathVariable("id") long id) {
        List<D11TeamTableStatDTO> d11TeamTableStats = getRepositoryService().findByD11MatchDayId(id);
        return ResponseEntity.ok(d11TeamTableStats);
    }

}
