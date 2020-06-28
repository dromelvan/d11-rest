package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.MatchDayDTO;
import org.d11.rest.service.MatchDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MatchDayController extends RepositoryController<MatchDayDTO, MatchDayService> {

    @Autowired
    public MatchDayController(MatchDayService matchDayService) {
        super(matchDayService);
    }

    @GetMapping(Endpoint.MATCH_DAYS)
    public ResponseEntity<List<MatchDayDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.MATCH_DAYS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.MATCH_DAY)
    public ResponseEntity<MatchDayDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.MATCH_DAY_CURRENT)
    public ResponseEntity<MatchDayDTO> findCurrentMatchDay() {
        MatchDayDTO matchDayDTO = getRepositoryService().findCurrentMatchDay();
        return ResponseEntity.ok(matchDayDTO);
    }

}
