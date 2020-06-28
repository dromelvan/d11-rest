package org.d11.rest.repository;

import java.util.*;

import org.d11.rest.model.jpa.D11League;
import org.springframework.data.jpa.repository.EntityGraph;

public interface D11LeagueRepository extends D11RestRepository<D11League> {

    public List<D11League> findByOrderBySeasonDateDesc();

    public Optional<D11League> findFirstByOrderBySeasonDateDesc();

    @Override
    @EntityGraph(attributePaths = { "d11MatchDays" })
    public Optional<D11League> findById(Long id);

}
