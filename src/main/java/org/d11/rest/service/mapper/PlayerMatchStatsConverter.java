package org.d11.rest.service.mapper;

import java.util.*;

import org.d11.rest.api.model.PlayerMatchStatDTO;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.util.PlayerMatchStats;
import org.modelmapper.*;

import com.google.common.collect.ComparisonChain;

public abstract class PlayerMatchStatsConverter<T extends Map<Long, Map<String, List<PlayerMatchStatDTO>>>> extends AbstractConverter<PlayerMatchStats, T> implements Comparator<PlayerMatchStat> {
    
    private ModelMapper modelMapper;
    
    public PlayerMatchStatsConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
  
    @Override
    public T convert(PlayerMatchStats playerMatchStats) {
        Collections.sort(playerMatchStats, this);
        T map = createMap();
        for (PlayerMatchStat playerMatchStat : playerMatchStats) {
            if (playerMatchStat.getLineup() >= 0) {
                Map<String, List<PlayerMatchStatDTO>> positionMap = map.get(getRootKey(playerMatchStat));
                if (positionMap == null) {
                    positionMap = new LinkedHashMap<String, List<PlayerMatchStatDTO>>();
                    map.put(getRootKey(playerMatchStat), positionMap);
                }
                List<PlayerMatchStatDTO> positionList = positionMap.get(playerMatchStat.getPosition().getName());
                if (positionList == null) {
                    positionList = new ArrayList<>();
                    positionMap.put(playerMatchStat.getPosition().getName(), positionList);
                }
                positionList.add(this.modelMapper.map(playerMatchStat, PlayerMatchStatDTO.class));
            }
        }
        return map;
    }
    
    protected abstract T createMap();
    
    protected abstract long getRootKey(PlayerMatchStat playerMatchStat);
    
    @Override
    public int compare(PlayerMatchStat playerMatchStat, PlayerMatchStat playerMatchStat2) {
        return ComparisonChain.start()
                              .compare(playerMatchStat.getPosition().getSortOrder(), playerMatchStat2.getPosition().getSortOrder())
                              .compare(playerMatchStat2.getLineup(), playerMatchStat.getLineup())
                              .result();
    }
    
}
