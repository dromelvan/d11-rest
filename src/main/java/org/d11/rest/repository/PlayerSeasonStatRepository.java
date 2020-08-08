package org.d11.rest.repository;

import java.util.Optional;

import org.d11.rest.model.jpa.PlayerSeasonStat;

public interface PlayerSeasonStatRepository extends D11RestRepository<PlayerSeasonStat> {

    public Optional<PlayerSeasonStat> findByPlayerIdAndSeasonId(Long playerId, Long seasonId);
    
}
