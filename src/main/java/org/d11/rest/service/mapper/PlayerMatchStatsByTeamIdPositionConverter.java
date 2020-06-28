package org.d11.rest.service.mapper;

import java.util.*;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.PlayerMatchStat;
import org.modelmapper.*;

import com.google.common.collect.ComparisonChain;

public class PlayerMatchStatsByTeamIdPositionConverter extends AbstractConverter<List<PlayerMatchStat>, PlayerMatchStatsByTeamIdPositionDTO> implements Comparator<PlayerMatchStat> {

    private ModelMapper modelMapper;

    public PlayerMatchStatsByTeamIdPositionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerMatchStatsByTeamIdPositionDTO convert(List<PlayerMatchStat> playerMatchStats) {
        Collections.sort(playerMatchStats, this);
        PlayerMatchStatsByTeamIdPositionDTO playerMatchStatsByTeamIdPositionDTO = new PlayerMatchStatsByTeamIdPositionDTO();
        for (PlayerMatchStat playerMatchStat : playerMatchStats) {
            if (playerMatchStat.getLineup() > 0) {
                Map<String, List<PlayerMatchStatDTO>> positionMap = playerMatchStatsByTeamIdPositionDTO.get(playerMatchStat.getTeam().getId());
                if (positionMap == null) {
                    positionMap = new LinkedHashMap<String, List<PlayerMatchStatDTO>>();
                    playerMatchStatsByTeamIdPositionDTO.put(playerMatchStat.getTeam().getId(), positionMap);
                }
                List<PlayerMatchStatDTO> positionList = positionMap.get(playerMatchStat.getPosition().getName());
                if (positionList == null) {
                    positionList = new ArrayList<>();
                    positionMap.put(playerMatchStat.getPosition().getName(), positionList);
                }
                positionList.add(this.modelMapper.map(playerMatchStat, PlayerMatchStatDTO.class));
            }
        }
        return playerMatchStatsByTeamIdPositionDTO;
    }
    
    @Override
    public int compare(PlayerMatchStat playerMatchStat, PlayerMatchStat playerMatchStat2) {
        return ComparisonChain.start()
                              .compare(playerMatchStat.getPosition().getSortOrder(), playerMatchStat2.getPosition().getSortOrder())
                              .compare(playerMatchStat2.getLineup(), playerMatchStat.getLineup())
                              .result();
    }

}
