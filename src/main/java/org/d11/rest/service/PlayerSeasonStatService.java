package org.d11.rest.service;

import java.util.Optional;

import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.model.jpa.PlayerSeasonStat;
import org.d11.rest.repository.PlayerSeasonStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerSeasonStatService extends RepositoryService<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatRepository> {

    @Autowired
    public PlayerSeasonStatService(PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(playerSeasonStatRepository);
    }

    public PlayerSeasonStatDTO findByPlayerIdAndSeasonId(long playerId, long seasonId) {
        Optional<PlayerSeasonStat> optional = getJpaRepository().findByPlayerIdAndSeasonId(playerId, seasonId);
        if(optional.isPresent()) {
            PlayerSeasonStat playerSeasonStat = optional.get();
            return map(playerSeasonStat, PlayerSeasonStatDTO.class);            
        }
        return find(Optional.empty());
    }
    
    @Override
    protected Class<PlayerSeasonStatDTO> getDTOClass() {
        return PlayerSeasonStatDTO.class;
    }
    
}
