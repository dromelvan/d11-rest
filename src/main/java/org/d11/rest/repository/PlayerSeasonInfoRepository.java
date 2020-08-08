package org.d11.rest.repository;

import java.util.Optional;

import org.d11.rest.model.jpa.PlayerSeasonInfo;

public interface PlayerSeasonInfoRepository extends D11RestRepository<PlayerSeasonInfo> {

    public Optional<PlayerSeasonInfo> findByPlayerIdAndSeasonId(Long playerId, Long seasonId);
    
}
