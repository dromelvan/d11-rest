package org.d11.rest.util;

import java.util.*;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.*;

import com.google.common.collect.ComparisonChain;

public abstract class PlayerMatchStatsByPositionConverter<T extends PlayerMatchStatsByPositionDTO> extends AbstractConverter<PlayerMatchStats, T> implements Comparator<PlayerMatchStat> {

    private ModelMapper modelMapper;

    public PlayerMatchStatsByPositionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    protected T convert(PlayerMatchStats playerMatchStats) {
        Collections.sort(playerMatchStats, this);
        T playerMatchStatsByPosition = createPlayerMatchStatsByPosition();
        for (PlayerMatchStat playerMatchStat : playerMatchStats) {
            if (playerMatchStat.getLineup() > 0) {
                Map<String, List<PlayerMatchStatDetailsDTO>> positionMap = playerMatchStatsByPosition.get(getRootKey(playerMatchStat));
                if (positionMap == null) {
                    positionMap = new LinkedHashMap<String, List<PlayerMatchStatDetailsDTO>>();
                    playerMatchStatsByPosition.put(getRootKey(playerMatchStat), positionMap);
                }
                List<PlayerMatchStatDetailsDTO> positionList = positionMap.get(playerMatchStat.getPosition().getName());
                if (positionList == null) {
                    positionList = new ArrayList<>();
                    positionMap.put(playerMatchStat.getPosition().getName(), positionList);
                }
                positionList.add(this.modelMapper.map(playerMatchStat, PlayerMatchStatDetailsDTO.class));
            }
        }
        return playerMatchStatsByPosition;
    }
    
    protected abstract Long getRootKey(PlayerMatchStat playerMatchStat);
    
    protected abstract T createPlayerMatchStatsByPosition();
    
    @Override
    public int compare(PlayerMatchStat playerMatchStat, PlayerMatchStat playerMatchStat2) {
        return ComparisonChain.start()
                              .compare(playerMatchStat.getPosition().getSortOrder(), playerMatchStat2.getPosition().getSortOrder())
                              .compare(playerMatchStat2.getLineup(), playerMatchStat.getLineup())
                              .result();
    }

}
