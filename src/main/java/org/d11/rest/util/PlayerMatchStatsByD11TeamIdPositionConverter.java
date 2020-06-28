package org.d11.rest.util;

import java.util.*;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.*;

import com.google.common.collect.ComparisonChain;

public class PlayerMatchStatsByD11TeamIdPositionConverter extends AbstractConverter<PlayerMatchStats, PlayerMatchStatsByD11TeamIdPositionDTO> implements Comparator<PlayerMatchStat> {

    private ModelMapper modelMapper;

    public PlayerMatchStatsByD11TeamIdPositionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerMatchStatsByD11TeamIdPositionDTO convert(PlayerMatchStats playerMatchStats) {
        Collections.sort(playerMatchStats, this);
        PlayerMatchStatsByD11TeamIdPositionDTO playerMatchStatsByD11TeamIdPositionDTO = new PlayerMatchStatsByD11TeamIdPositionDTO();
        for (PlayerMatchStat playerMatchStat : playerMatchStats) {
            if (playerMatchStat.getLineup() > 0) {
                Map<String, List<PlayerMatchStatDetailsDTO>> positionMap = playerMatchStatsByD11TeamIdPositionDTO.get(playerMatchStat.getD11Team().getId());
                if (positionMap == null) {
                    positionMap = new LinkedHashMap<String, List<PlayerMatchStatDetailsDTO>>();
                    playerMatchStatsByD11TeamIdPositionDTO.put(playerMatchStat.getD11Team().getId(), positionMap);
                }
                List<PlayerMatchStatDetailsDTO> positionList = positionMap.get(playerMatchStat.getPosition().getName());
                if (positionList == null) {
                    positionList = new ArrayList<>();
                    positionMap.put(playerMatchStat.getPosition().getName(), positionList);
                }
                positionList.add(this.modelMapper.map(playerMatchStat, PlayerMatchStatDetailsDTO.class));
            }
        }
        return playerMatchStatsByD11TeamIdPositionDTO;
    }
    
    @Override
    public int compare(PlayerMatchStat playerMatchStat, PlayerMatchStat playerMatchStat2) {
        return ComparisonChain.start()
                              .compare(playerMatchStat.getPosition().getSortOrder(), playerMatchStat2.getPosition().getSortOrder())
                              .compare(playerMatchStat2.getLineup(), playerMatchStat.getLineup())
                              .result();
    }

}
