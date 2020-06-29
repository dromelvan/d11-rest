package org.d11.rest.service.mapper;

import org.d11.rest.api.collection.MatchPlayerMatchStatsDTO;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.ModelMapper;

public class MatchPlayerMatchStatsConverter extends PlayerMatchStatsConverter<MatchPlayerMatchStatsDTO> {

    public MatchPlayerMatchStatsConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    protected MatchPlayerMatchStatsDTO createMap() {
        return new MatchPlayerMatchStatsDTO();
    }

    @Override
    protected long getRootKey(PlayerMatchStat playerMatchStat) {
        return playerMatchStat.getTeam().getId();
    }
    
}
