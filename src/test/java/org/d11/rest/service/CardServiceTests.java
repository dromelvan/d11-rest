package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.cards;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.team;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.CardDTO;
import org.d11.rest.model.jpa.Card;
import org.d11.rest.repository.CardRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class CardServiceTests extends RepositoryServiceTests<Card, CardDTO, CardRepository, CardService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new CardService(mock(CardRepository.class)));
		List<Card> cards = cards();
		for(Card card : cards) {
			match().addCard(card);
			card.setTeam(team());
			card.setPlayer(player());
		}
		setD11RestEntities(cards);
		
		super.beforeAll();
	}	
	
}
