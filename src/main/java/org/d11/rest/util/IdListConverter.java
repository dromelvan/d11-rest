package org.d11.rest.util;

import java.util.List;
import java.util.stream.Collectors;

import org.d11.rest.model.jpa.D11RestEntity;
import org.modelmapper.AbstractConverter;

public class IdListConverter extends AbstractConverter<List<D11RestEntity>, List<Long>> {

    @Override
    public List<Long> convert(List<D11RestEntity> d11RestEntities) {
        return d11RestEntities.stream().map(d11RestEntity -> d11RestEntity.getId()).collect(Collectors.toList());
    }

}
