package org.d11.rest.repository;

import java.util.List;
import java.util.Optional;

import org.d11.rest.model.jpa.PremierLeague;
import org.springframework.data.jpa.repository.EntityGraph;

public interface PremierLeagueRepository extends D11RestRepository<PremierLeague> {

    public List<PremierLeague> findByOrderBySeasonDateDesc();

    public Optional<EntityId> findFirstByOrderBySeasonDateDesc();

    @Override
    @EntityGraph(attributePaths = { "matchDays" })
    public Optional<PremierLeague> findById(Long id);

}
