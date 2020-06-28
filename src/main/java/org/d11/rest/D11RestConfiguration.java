package org.d11.rest;

import javax.persistence.EntityManagerFactory;

import org.d11.rest.service.mapper.D11RestModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class D11RestConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper modelMapper(EntityManagerFactory entityManagerFactory) {
        ModelMapper modelMapper = new D11RestModelMapper(entityManagerFactory);
        return modelMapper;
    }

}
