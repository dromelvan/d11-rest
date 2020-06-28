package org.d11.rest.model.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.d11.rest.api.model.CardType;

@Converter
public class CardTypeConverter implements AttributeConverter<CardType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CardType cardType) {
        return cardType.getId();
    }

    @Override
    public CardType convertToEntityAttribute(Integer id) {
        return CardType.withId(id);
    }

}
