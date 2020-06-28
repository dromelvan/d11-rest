package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.CardDTO;
import org.d11.rest.controller.CardController;
import org.d11.rest.model.jpa.Card;
import org.d11.rest.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class CardEndpointTests extends SeasonMockEndpointTests<Card, CardDTO, CardController> {

	@Override
	protected CardDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<CardDTO>() {});
	}
	
	@Override
	protected List<CardDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<CardDTO>>() {});
	}

	@BeforeEach
	public void beforeEach() {
		super.beforeEach();		
		CardRepository cardRepository = getRepository(CardRepository.class);
		setD11RestEntities(cardRepository.findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.CARDS);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.CARDS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.CARD);
	}
	
}
