package org.d11.rest.service;

import org.d11.rest.api.model.PlayerDTO;
import org.d11.rest.model.jpa.Player;
import org.d11.rest.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends RepositoryService<Player, PlayerDTO, PlayerRepository> {

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        super(playerRepository);
    }

    @Override
    protected Class<PlayerDTO> getDTOClass() {
        return PlayerDTO.class;
    }

}
