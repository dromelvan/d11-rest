package org.d11.rest.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.d11.rest.model.jpa.D11MatchDay;
import org.d11.rest.model.jpa.projection.EntityId;
import org.springframework.data.jpa.repository.EntityGraph;

public interface D11MatchDayRepository extends D11RestRepository<D11MatchDay> {

    public Optional<EntityId> findFirstByDateLessThanEqualOrderByDateDesc(LocalDate date);

    @Override
    @EntityGraph(attributePaths = { "d11Matches" })
    public Optional<D11MatchDay> findById(Long id);

}
