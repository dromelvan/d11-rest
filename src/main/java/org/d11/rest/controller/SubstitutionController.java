package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.SubstitutionDTO;
import org.d11.rest.service.SubstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SubstitutionController extends RepositoryController<SubstitutionDTO, SubstitutionService> {

    @Autowired
    public SubstitutionController(SubstitutionService playerService) {
        super(playerService);
    }

    @GetMapping(Endpoint.SUBSTITUTIONS)
    public ResponseEntity<List<SubstitutionDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.SUBSTITUTIONS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.SUBSTITUTION)
    public ResponseEntity<SubstitutionDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
