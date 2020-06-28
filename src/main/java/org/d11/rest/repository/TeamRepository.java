package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.Team;
import org.d11.rest.model.jpa.projection.EntityId;

public interface TeamRepository extends D11RestRepository<Team> {

    public List<Team> findByOrderByNameAsc();

    public List<EntityId> findIdByOrderByNameAsc();

}
