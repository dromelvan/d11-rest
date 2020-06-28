package org.d11.rest.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.d11.rest.model.jpa.MatchDay;
import org.springframework.data.jpa.repository.EntityGraph;

public interface MatchDayRepository extends D11RestRepository<MatchDay> {

    public Optional<EntityId> findFirstByDateLessThanEqualOrderByDateDesc(LocalDate date);

    @Override
    @EntityGraph(attributePaths = { "matches" })
    public Optional<MatchDay> findById(Long id);

}
