package org.d11.rest.service;

import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.model.jpa.PlayerSeasonInfo;
import org.d11.rest.repository.PlayerSeasonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerSeasonInfoService extends RepositoryService<PlayerSeasonInfo, PlayerSeasonInfoDTO, PlayerSeasonInfoRepository> {

    @Autowired
    public PlayerSeasonInfoService(PlayerSeasonInfoRepository playerSeasonInfoRepository) {
        super(playerSeasonInfoRepository);
    }

    @Override
    protected Class<PlayerSeasonInfoDTO> getDTOClass() {
        return PlayerSeasonInfoDTO.class;
    }
    
}
