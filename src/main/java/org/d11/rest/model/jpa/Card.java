package org.d11.rest.model.jpa;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.d11.rest.api.model.CardType;
import org.d11.rest.model.jpa.converter.CardTypeConverter;

@Entity
@Table(name = "cards")
public class Card extends MatchEvent {

    @NotNull
    @Convert(converter = CardTypeConverter.class)
    private CardType cardType;

    protected Card() {
    }

    public Card(Integer time, Integer addedTime, CardType cardType) {
        super(time, addedTime);
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

}
