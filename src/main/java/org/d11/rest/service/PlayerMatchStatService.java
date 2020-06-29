package org.d11.rest.service;

import org.d11.rest.api.collection.*;
import org.d11.rest.api.model.PlayerMatchStatBaseDTO;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.repository.PlayerMatchStatRepository;
import org.d11.rest.util.PlayerMatchStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatService extends RepositoryService<PlayerMatchStat, PlayerMatchStatBaseDTO, PlayerMatchStatRepository> {

    @Autowired
    public PlayerMatchStatService(PlayerMatchStatRepository playerMatchStatRepository) {
        super(playerMatchStatRepository);
    }

    public MatchPlayerMatchStatsDTO findByMatchId(long d11MatchId) {
        PlayerMatchStats playerMatchStats = getJpaRepository().findByMatchId(d11MatchId);
        return map(playerMatchStats, MatchPlayerMatchStatsDTO.class);
    }
    
    public D11MatchPlayerMatchStatsDTO findByD11MatchId(long d11MatchId) {
        PlayerMatchStats playerMatchStats = getJpaRepository().findByD11MatchId(d11MatchId);
        return map(playerMatchStats, D11MatchPlayerMatchStatsDTO.class);
    }
    
    @Override
    protected Class<PlayerMatchStatBaseDTO> getDTOClass() {
        return PlayerMatchStatBaseDTO.class;
    }
    
}
