package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11MatchDTO;
import org.d11.rest.service.D11MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class D11MatchController extends RepositoryController<D11MatchDTO, D11MatchService> {

    @Autowired
    public D11MatchController(D11MatchService d11MatchService) {
        super(d11MatchService);
    }

    @Override
    @GetMapping(Endpoint.D11_MATCHES)
    public ResponseEntity<List<D11MatchDTO>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping(Endpoint.D11_MATCHES_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @Override
    @GetMapping(Endpoint.D11_MATCH)
    public ResponseEntity<D11MatchDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
