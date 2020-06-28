package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11TeamDTO;
import org.d11.rest.service.D11TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class D11TeamController extends RepositoryController<D11TeamDTO, D11TeamService> {

    @Autowired
    public D11TeamController(D11TeamService d11TeamService) {
        super(d11TeamService);
    }

    @GetMapping(Endpoint.D11_TEAMS)
    public ResponseEntity<List<D11TeamDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.D11_TEAMS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.D11_TEAM)
    public ResponseEntity<D11TeamDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
