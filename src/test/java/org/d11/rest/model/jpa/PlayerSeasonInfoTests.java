package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonInfo;
import static org.d11.rest.model.D11RestMock.position;
import static org.d11.rest.model.D11RestMock.season;
import static org.d11.rest.model.D11RestMock.team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonInfoDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class PlayerSeasonInfoTests {

    @Test
    public void isValid() {
        PlayerSeasonInfo playerSeasonInfo = playerSeasonInfo();
        Player player = player();
        playerSeasonInfo.setPlayer(player);
        Season season = season();
        playerSeasonInfo.setSeason(season);
        Team team = team();
        playerSeasonInfo.setTeam(team);
        D11Team d11Team = d11Team();
        playerSeasonInfo.setD11Team(d11Team);
        Position position = position();
        playerSeasonInfo.setPosition(position);
        
        assertTrue(playerSeasonInfo.isValid());
        
        playerSeasonInfo.setPlayer(null);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setPlayer(player);

        playerSeasonInfo.setSeason(null);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setSeason(season);
        
        playerSeasonInfo.setTeam(null);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setTeam(team);
        
        playerSeasonInfo.setD11Team(null);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setD11Team(d11Team);
        
        playerSeasonInfo.setPosition(null);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setPosition(position);

        playerSeasonInfo.setValue(null);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setValue(-5);
        assertFalse(playerSeasonInfo.isValid());
        playerSeasonInfo.setValue(5);

        assertTrue(playerSeasonInfo.isValid());        
    }    
    
    @Test
    public void map() {
        PlayerSeasonInfo playerSeasonInfo = playerSeasonInfo();        
        Player player = player();
        playerSeasonInfo.setPlayer(player);
        Season season = season();
        playerSeasonInfo.setSeason(season);
        Team team = team();
        playerSeasonInfo.setTeam(team);
        D11Team d11Team = d11Team();
        playerSeasonInfo.setD11Team(d11Team);
        Position position = position();
        playerSeasonInfo.setPosition(position);

        ModelMapper modelMapper = new D11RestModelMapper();
        
        PlayerSeasonInfoDTO playerSeasonInfoDTO = modelMapper.map(playerSeasonInfo, PlayerSeasonInfoDTO.class);
        assertEqualsDTO(playerSeasonInfo, playerSeasonInfoDTO);
        
        playerSeasonInfoDTO.setValue(0);
        assertEquals("0.0", playerSeasonInfoDTO.getValueString());
        playerSeasonInfoDTO.setValue(5);
        assertEquals("0.5", playerSeasonInfoDTO.getValueString());
        playerSeasonInfoDTO.setValue(15);
        assertEquals("1.5", playerSeasonInfoDTO.getValueString());        
    }    
    
}
