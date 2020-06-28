package org.d11.rest.service;

import org.d11.rest.service.mapper.D11RestModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class D11RestService {

    @Autowired
    private ModelMapper modelMapper = new D11RestModelMapper();

    protected ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    protected <T> T map(Object object, Class<T> destinationType) {
        // HATEOAS.addLinks(entity);
        return this.modelMapper.map(object, destinationType);
    }

}
