package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.card;
import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.CardDTO;
import org.d11.rest.api.model.CardType;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class CardTests {

	@Test
	public void isValid() {
		Card card = card();
		Match match = match();
		match.addCard(card);
		Team team = team();
		card.setTeam(team);
		Player player = player();
		card.setPlayer(player);
		
		assertTrue(card.isValid());
		
		match.removeCard(card);
		assertFalse(card.isValid());
		match.addCard(card);
		
		card.setTeam(null);
		assertFalse(card.isValid());
		card.setTeam(team);
		
		card.setPlayer(null);
		assertFalse(card.isValid());
		card.setPlayer(player);

		card.setTime(-1);
		assertFalse(card.isValid());
		card.setTime(91);
		assertFalse(card.isValid());
		card.setTime(45);

		card.setAddedTime(-1);
		assertFalse(card.isValid());
		card.setAddedTime(5);
		
		card.setCardType(null);
		assertFalse(card.isValid());
		card.setCardType(CardType.YELLOW);
	}
	
	@Test
	public void map() {
		Card card = card();
		Match match = match();
		match.addCard(card);
		Team team = team();
		card.setTeam(team);
		Player player = player();
		card.setPlayer(player);

		ModelMapper modelMapper = new D11RestModelMapper();
		
		CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
		
		assertEqualsDTO(card, cardDTO);
		
		Card mappedCard = new Card();
		
		modelMapper.map(cardDTO, mappedCard);
		
		assertEquals(card.getId(), mappedCard.getId());
		assertEquals(card.getTime(), mappedCard.getTime());
		assertEquals(card.getAddedTime(), mappedCard.getAddedTime());
		assertEquals(card.getCardType(), mappedCard.getCardType());
		assertNull(mappedCard.getMatch());
		assertNull(mappedCard.getTeam());
		assertNull(mappedCard.getPlayer());
	}

}
