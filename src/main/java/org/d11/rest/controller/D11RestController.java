package org.d11.rest.controller;

import org.d11.rest.service.mapper.D11RestModelMapper;
import org.modelmapper.ModelMapper;

public class D11RestController {

    private ModelMapper modelMapper = new D11RestModelMapper();

    protected <T> T map(Object source, Class<T> destinationType) {
        return this.modelMapper.map(source, destinationType);
    }

}
