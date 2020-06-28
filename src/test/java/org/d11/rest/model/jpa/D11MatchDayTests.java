package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.d11Matches;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.season;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11MatchDayDTO;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class D11MatchDayTests {

    @Test
    public void isValid() {
        D11MatchDay d11MatchDay = d11MatchDay();
        d11MatchDay.setD11League(d11League());
        d11MatchDay.setMatchDay(matchDay());

        assertTrue(d11MatchDay.isValid());

        d11MatchDay.setD11League(null);
        assertFalse(d11MatchDay.isValid());
        d11MatchDay.setD11League(d11League());
        assertTrue(d11MatchDay.isValid());

        d11MatchDay.setMatchDay(null);
        assertFalse(d11MatchDay.isValid());
        d11MatchDay.setMatchDay(matchDay());
        assertTrue(d11MatchDay.isValid());

        d11MatchDay.setDate(null);
        assertFalse(d11MatchDay.isValid());
        d11MatchDay.setDate(LocalDate.now());
        assertTrue(d11MatchDay.isValid());

        d11MatchDay.setMatchDayNumber(null);
        assertFalse(d11MatchDay.isValid());
        d11MatchDay.setMatchDayNumber(0);
        assertFalse(d11MatchDay.isValid());
        d11MatchDay.setMatchDayNumber(39);
        assertFalse(d11MatchDay.isValid());
        d11MatchDay.setMatchDayNumber(1);
        assertTrue(d11MatchDay.isValid());
    }

    @Test
    public void references() {
        D11MatchDay d11MatchDay = d11MatchDay();
        List<D11Match> d11Matches = d11Matches();

        d11Matches.forEach(d11Match -> d11MatchDay.addD11Match(d11Match));
        d11Matches.forEach(d11Match -> assertEquals(d11MatchDay, d11Match.getD11MatchDay()));

        d11Matches.forEach(d11Match -> d11MatchDay.removeD11Match(d11Match));
        d11Matches.forEach(d11Match -> assertEquals(null, d11Match.getD11MatchDay()));
    }

    @Test
    public void map() {
        D11MatchDay d11MatchDay = d11MatchDay();
        d11MatchDay.setD11League(d11League());
        d11MatchDay.getD11League().setSeason(season());
        d11MatchDay.setMatchDay(matchDay());

        ModelMapper modelMapper = new D11RestModelMapper();

        D11MatchDayDTO d11MatchDayDTO = modelMapper.map(d11MatchDay, D11MatchDayDTO.class);

        assertEqualsDTO(d11MatchDay, d11MatchDayDTO);

        D11MatchDay mappedD11MatchDay = new D11MatchDay();

        modelMapper.map(d11MatchDayDTO, mappedD11MatchDay);

        assertEquals(d11MatchDay.getId(), mappedD11MatchDay.getId());
        assertEquals(d11MatchDay.getDate(), mappedD11MatchDay.getDate());
        assertEquals(d11MatchDay.getMatchDayNumber(), mappedD11MatchDay.getMatchDayNumber());
        assertNull(mappedD11MatchDay.getD11League());
        assertNull(mappedD11MatchDay.getMatchDay());
    }

}
