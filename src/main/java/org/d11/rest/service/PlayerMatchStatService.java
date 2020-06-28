package org.d11.rest.service;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.repository.PlayerMatchStatRepository;
import org.d11.rest.util.PlayerMatchStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatService extends RepositoryService<PlayerMatchStat, PlayerMatchStatDTO, PlayerMatchStatRepository> {

    @Autowired
    public PlayerMatchStatService(PlayerMatchStatRepository playerMatchStatRepository) {
        super(playerMatchStatRepository);
    }

    public PlayerMatchStatsByD11TeamIdPositionDTO findByD11MatchId(long d11MatchId) {
        PlayerMatchStats playerMatchStats = getJpaRepository().findByD11MatchId(d11MatchId);
        if(playerMatchStats == null) {
            playerMatchStats = new PlayerMatchStats();
        }
        return map(playerMatchStats, PlayerMatchStatsByD11TeamIdPositionDTO.class);
    }
    
    @Override
    protected Class<PlayerMatchStatDTO> getDTOClass() {
        return PlayerMatchStatDTO.class;
    }
    
}
