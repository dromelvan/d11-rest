package org.d11.rest.service;

import org.d11.rest.api.model.CardDTO;
import org.d11.rest.model.jpa.Card;
import org.d11.rest.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService extends RepositoryService<Card, CardDTO, CardRepository> {

    @Autowired
    public CardService(CardRepository cardRepository) {
        super(cardRepository);
    }

    @Override
    protected Class<CardDTO> getDTOClass() {
        return CardDTO.class;
    }

}
