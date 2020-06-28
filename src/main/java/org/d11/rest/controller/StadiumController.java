package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.StadiumDTO;
import org.d11.rest.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StadiumController extends RepositoryController<StadiumDTO, StadiumService> {

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        super(stadiumService);
    }

    @GetMapping(Endpoint.STADIA)
    public ResponseEntity<List<StadiumDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.STADIA_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.STADIUM)
    public ResponseEntity<StadiumDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
