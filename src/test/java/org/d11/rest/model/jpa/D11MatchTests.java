package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11Match;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.d11.rest.Tags;
import org.d11.rest.api.model.*;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class D11MatchTests {

    @Test
    public void isValid() {
        D11Match d11Match = d11Match();
        D11Team homeD11Team = d11Team(1);
        D11Team awayD11Team = d11Team(2);
        D11MatchDay d11MatchDay = d11MatchDay();

        d11Match.setHomeD11Team(homeD11Team);
        d11Match.setAwayD11Team(awayD11Team);
        d11MatchDay.addD11Match(d11Match);

        assertTrue(d11Match.isValid());

        d11Match.setHomeD11Team(null);
        assertFalse(d11Match.isValid());
        d11Match.setHomeD11Team(homeD11Team);

        d11Match.setAwayD11Team(null);
        assertFalse(d11Match.isValid());
        d11Match.setAwayD11Team(awayD11Team);

        d11MatchDay.removeD11Match(d11Match);
        assertFalse(d11Match.isValid());
        d11MatchDay.addD11Match(d11Match);

        d11Match.setHomeTeamGoals(null);
        assertFalse(d11Match.isValid());
        d11Match.setHomeTeamGoals(-1);
        assertFalse(d11Match.isValid());
        d11Match.setHomeTeamGoals(0);

        d11Match.setAwayTeamGoals(null);
        assertFalse(d11Match.isValid());
        d11Match.setAwayTeamGoals(-1);
        assertFalse(d11Match.isValid());
        d11Match.setAwayTeamGoals(0);

        d11Match.setHomeTeamPoints(null);
        assertFalse(d11Match.isValid());
        d11Match.setHomeTeamPoints(0);

        d11Match.setAwayTeamPoints(null);
        assertFalse(d11Match.isValid());
        d11Match.setAwayTeamPoints(0);

        d11Match.setPreviousHomeTeamGoals(null);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousHomeTeamGoals(-1);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousHomeTeamGoals(0);

        d11Match.setPreviousAwayTeamGoals(null);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousAwayTeamGoals(-1);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousAwayTeamGoals(0);

        d11Match.setPreviousHomeTeamPoints(null);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousHomeTeamPoints(0);

        d11Match.setPreviousAwayTeamPoints(null);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousAwayTeamPoints(0);

        d11Match.setDate(null);
        assertFalse(d11Match.isValid());
        d11Match.setDate(LocalDate.now());

        d11Match.setElapsed(null);
        assertFalse(d11Match.isValid());
        d11Match.setElapsed("NA");

        d11Match.setStatus(null);
        assertFalse(d11Match.isValid());
        d11Match.setStatus(Status.PENDING);

        assertTrue(d11Match.isValid());
    }

    @Test
    public void map() {
        D11Match d11Match = d11Match();
        D11Team homeD11Team = d11Team(1);
        D11Team awayD11Team = d11Team(2);
        D11MatchDay d11MatchDay = d11MatchDay();

        d11Match.setHomeD11Team(homeD11Team);
        d11Match.setAwayD11Team(awayD11Team);
        d11MatchDay.addD11Match(d11Match);

        ModelMapper modelMapper = new D11RestModelMapper();

        D11MatchDTO d11MatchDTO = modelMapper.map(d11Match, D11MatchDTO.class);

        assertEqualsDTO(d11Match, d11MatchDTO);

        D11Match mappedD11Match = new D11Match();

        modelMapper.map(d11MatchDTO, mappedD11Match);

        assertEquals(d11Match.getId(), d11MatchDTO.getId());
        assertEquals(d11Match.getHomeTeamGoals(), d11MatchDTO.getHomeTeamGoals());
        assertEquals(d11Match.getAwayTeamGoals(), d11MatchDTO.getAwayTeamGoals());
        assertEquals(d11Match.getHomeTeamPoints(), d11MatchDTO.getHomeTeamPoints());
        assertEquals(d11Match.getAwayTeamPoints(), d11MatchDTO.getAwayTeamPoints());
        assertEquals(d11Match.getPreviousHomeTeamGoals(), d11MatchDTO.getPreviousHomeTeamGoals());
        assertEquals(d11Match.getPreviousAwayTeamGoals(), d11MatchDTO.getPreviousAwayTeamGoals());
        assertEquals(d11Match.getPreviousHomeTeamPoints(), d11MatchDTO.getPreviousHomeTeamPoints());
        assertEquals(d11Match.getPreviousAwayTeamPoints(), d11MatchDTO.getPreviousAwayTeamPoints());
        assertEquals(d11Match.getElapsed(), d11MatchDTO.getElapsed());
        assertEquals(d11Match.getStatus(), d11MatchDTO.getStatus());
        assertNull(mappedD11Match.getHomeD11Team());
        assertNull(mappedD11Match.getAwayD11Team());
        assertNull(mappedD11Match.getD11MatchDay());
    }

}
