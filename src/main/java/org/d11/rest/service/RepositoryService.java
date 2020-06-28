package org.d11.rest.service;

import java.util.*;
import java.util.stream.Collectors;

import org.d11.rest.api.model.D11RestApiDTO;
import org.d11.rest.model.jpa.D11RestEntity;
import org.d11.rest.model.jpa.projection.EntityId;
import org.d11.rest.repository.*;
import org.d11.rest.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class RepositoryService<T extends D11RestEntity, U extends D11RestApiDTO, V extends D11RestRepository<T>> extends D11RestService {

    private V jpaRepository;

    public RepositoryService(V jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    protected V getJpaRepository() {
        return this.jpaRepository;
    }

    public List<U> findAll() {
        return map(this.jpaRepository.findAll());
    }

    public List<U> findAll(Sort sort) {
        return map(this.jpaRepository.findAll(sort));
    }

    public List<Long> findAllIds() {
        List<EntityId> entityIds = findAllEntityIds();
        return entityIds.stream().map(entityId -> entityId.getId()).collect(Collectors.toList());
    }

    public U findById(int id) {
        return findById((long) id);
    }

    public U findById(long id) {
        Optional<T> optional = this.jpaRepository.findById(id);
        return find(optional);
    }

    protected List<EntityId> findAllEntityIds() {
        return this.jpaRepository.findAllByOrderById();
    }

    protected U find(Optional<T> optional) {
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new NotFoundException();
    }

    protected U map(T entity) {
        return map(entity, getDTOClass());
    }

    protected List<U> map(List<T> entities) {
        return map(entities, getDTOClass());
    }

    protected <C extends D11RestApiDTO> List<C> map(List<T> entities, Class<C> destinationType) {
        return entities.stream().map(entity -> map(entity, destinationType)).collect(Collectors.toList());
    }
    
    protected abstract Class<U> getDTOClass();

}
