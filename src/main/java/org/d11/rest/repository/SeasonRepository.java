package org.d11.rest.repository;

import java.util.List;
import java.util.Optional;

import org.d11.rest.model.jpa.Season;

public interface SeasonRepository extends D11RestRepository<Season> {

    public List<Season> findByOrderByDateDesc();

    public List<EntityId> findIdByOrderByDateDesc();

    public Optional<Season> findFirstByOrderByDateDesc();

}
