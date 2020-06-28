package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11MatchDayDTO;
import org.d11.rest.service.D11MatchDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class D11MatchDayController extends RepositoryController<D11MatchDayDTO, D11MatchDayService> {

    @Autowired
    public D11MatchDayController(D11MatchDayService d11MatchDayService) {
        super(d11MatchDayService);
    }

    @Override
    @GetMapping(Endpoint.D11_MATCH_DAYS)
    public ResponseEntity<List<D11MatchDayDTO>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping(Endpoint.D11_MATCH_DAYS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @Override
    @GetMapping(Endpoint.D11_MATCH_DAY)
    public ResponseEntity<D11MatchDayDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

    @GetMapping(Endpoint.D11_MATCH_DAY_CURRENT)
    public ResponseEntity<D11MatchDayDTO> findCurrentD11MatchDay() {
        D11MatchDayDTO d11MatchDayDTO = getRepositoryService().findCurrentD11MatchDay();
        return ResponseEntity.ok(d11MatchDayDTO);
    }

}
