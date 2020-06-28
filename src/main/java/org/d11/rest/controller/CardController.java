package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.CardDTO;
import org.d11.rest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CardController extends RepositoryController<CardDTO, CardService> {

    @Autowired
    public CardController(CardService playerService) {
        super(playerService);
    }

    @GetMapping(Endpoint.CARDS)
    public ResponseEntity<List<CardDTO>> findAll() {
        return super.findAll();
    }

    @GetMapping(Endpoint.CARDS_IDS)
    public ResponseEntity<List<Long>> findAllIds() {
        return super.findAllIds();
    }

    @GetMapping(Endpoint.CARD)
    public ResponseEntity<CardDTO> findById(@PathVariable("id") long id) {
        return super.findById(id);
    }

}
