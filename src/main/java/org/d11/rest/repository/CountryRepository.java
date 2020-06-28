package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.Country;
import org.d11.rest.model.jpa.projection.EntityId;

public interface CountryRepository extends D11RestRepository<Country> {

    public List<Country> findByOrderByNameAsc();

    public List<EntityId> findIdByOrderByNameAsc();

}
