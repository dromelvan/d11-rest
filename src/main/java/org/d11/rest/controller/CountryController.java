package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.CountryDTO;
import org.d11.rest.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CountryController extends RepositoryController<CountryDTO, CountryService> {

    @Autowired
    public CountryController(CountryService countryService) {
        super(countryService);
    }

    @GetMapping(Endpoint.COUNTRIES)
    public ResponseEntity<List<CountryDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.COUNTRIES_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.COUNTRY)
    public ResponseEntity<CountryDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
