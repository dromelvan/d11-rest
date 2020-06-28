package org.d11.rest.util;

import org.d11.rest.api.model.PlayerMatchStatsByD11TeamIdPositionDTO;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.ModelMapper;

public class PlayerMatchStatsByD11TeamPositionConverter extends PlayerMatchStatsByPositionConverter<PlayerMatchStatsByD11TeamIdPositionDTO> {

    public PlayerMatchStatsByD11TeamPositionConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected Long getRootKey(PlayerMatchStat playerMatchStat) {
        return playerMatchStat.getD11Team().getId();
    }

    @Override
    protected PlayerMatchStatsByD11TeamIdPositionDTO createPlayerMatchStatsByPosition() {
        return new PlayerMatchStatsByD11TeamIdPositionDTO();
    }

}
