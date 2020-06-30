package org.d11.rest.service.mapper;

import org.d11.rest.api.collection.D11MatchPlayerMatchStatsDTO;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.ModelMapper;

public class D11MatchPlayerMatchStatsConverter extends PlayerMatchStatsConverter<D11MatchPlayerMatchStatsDTO> {

    public D11MatchPlayerMatchStatsConverter(ModelMapper modelMapper) {
        super(modelMapper, true);
    }

    protected D11MatchPlayerMatchStatsDTO createMap() {
        return new D11MatchPlayerMatchStatsDTO();
    }

    @Override
    protected long getRootKey(PlayerMatchStat playerMatchStat) {
        return playerMatchStat.getD11Team().getId();
    }
    
}
