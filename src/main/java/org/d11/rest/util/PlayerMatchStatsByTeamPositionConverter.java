package org.d11.rest.util;

import org.d11.rest.api.model.PlayerMatchStatsByTeamIdPositionDTO;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.ModelMapper;

public class PlayerMatchStatsByTeamPositionConverter extends PlayerMatchStatsByPositionConverter<PlayerMatchStatsByTeamIdPositionDTO> {

    public PlayerMatchStatsByTeamPositionConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected Long getRootKey(PlayerMatchStat playerMatchStat) {
        return playerMatchStat.getTeam().getId();
    }

    @Override
    protected PlayerMatchStatsByTeamIdPositionDTO createPlayerMatchStatsByPosition() {
        return new PlayerMatchStatsByTeamIdPositionDTO();
    }
    
}
