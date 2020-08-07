package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.playerSeasonStat;
import static org.d11.rest.model.D11RestMock.season;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.api.model.PlayerSeasonStatDTO;
import org.d11.rest.service.mapper.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class PlayerSeasonStatTests extends PlayerStatTests {

    @Test
    public void isValid() {
        PlayerSeasonStat playerSeasonStat = playerSeasonStat();
        Player player = player();
        playerSeasonStat.setPlayer(player);
        Season season = season();
        playerSeasonStat.setSeason(season);

        assertTrue(playerSeasonStat.isValid());
        
        isValid(playerSeasonStat);
        
        playerSeasonStat.setPlayer(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setPlayer(player);

        playerSeasonStat.setSeason(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setSeason(season);
        
        playerSeasonStat.setCleanSheets(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setCleanSheets(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setCleanSheets(0);

        playerSeasonStat.setYellowCards(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setYellowCards(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setYellowCards(0);

        playerSeasonStat.setRedCards(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setRedCards(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setRedCards(0);

        playerSeasonStat.setManOfTheMatch(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setManOfTheMatch(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setManOfTheMatch(0);

        playerSeasonStat.setSharedManOfTheMatch(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setSharedManOfTheMatch(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setSharedManOfTheMatch(0);
        
        playerSeasonStat.setGamesStarted(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setGamesStarted(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setGamesStarted(0);

        playerSeasonStat.setGamesSubstitute(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setGamesSubstitute(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setGamesSubstitute(0);
        
        playerSeasonStat.setGamesDidNotParticipate(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setGamesDidNotParticipate(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setGamesDidNotParticipate(0);

        playerSeasonStat.setSubstitutionsOn(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setSubstitutionsOn(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setSubstitutionsOn(0);

        playerSeasonStat.setSubstitutionsOff(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setSubstitutionsOff(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setSubstitutionsOff(0);

        playerSeasonStat.setMinutesPlayed(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setMinutesPlayed(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setMinutesPlayed(0);

        playerSeasonStat.setFormPoints(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setFormPoints(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setFormPoints(0);

        playerSeasonStat.setPointsPerAppearance(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setPointsPerAppearance(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setPointsPerAppearance(0);

        playerSeasonStat.setRanking(null);
        assertFalse(playerSeasonStat.isValid());              
        playerSeasonStat.setRanking(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setRanking(0);
        
        assertTrue(playerSeasonStat.isValid());
    }

    @Test
    public void map() {
        PlayerSeasonStat playerSeasonStat = playerSeasonStat();        
        Player player = player();
        playerSeasonStat.setPlayer(player);
        Season season = season();
        playerSeasonStat.setSeason(season);
        
        ModelMapper modelMapper = new D11RestModelMapper();
        
        PlayerSeasonStatDTO playerSeasonStatDTO = modelMapper.map(playerSeasonStat, PlayerSeasonStatDTO.class);
        assertEqualsDTO(playerSeasonStat, playerSeasonStatDTO);        
    }    
    
}
