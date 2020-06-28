package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.PositionDTO;
import org.d11.rest.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PositionController extends RepositoryController<PositionDTO, PositionService> {

    @Autowired
    public PositionController(PositionService positionService) {
        super(positionService);
    }

    @GetMapping(Endpoint.POSITIONS)
    public ResponseEntity<List<PositionDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.POSITIONS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.POSITION)
    public ResponseEntity<PositionDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
