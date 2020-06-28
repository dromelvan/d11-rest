package org.d11.rest.model.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.d11.rest.api.model.Status;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer id) {
        return Status.withId(id);
    }

}
