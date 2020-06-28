package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDays;
import static org.d11.rest.model.D11RestMock.season;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11LeagueDTO;
import org.d11.rest.util.D11RestModelMapper;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

@Tag(Tags.UNIT_TEST)
public class D11LeagueTests {

    @Test
    public void isValid() {
        D11League d11League = new D11League("Name");
        d11League.setSeason(season());

        assertTrue(d11League.isValid());

        d11League.setName(null);
        assertFalse(d11League.isValid());
        d11League.setName("");
        assertFalse(d11League.isValid());
        d11League.setName("Name");

        d11League.setSeason(null);
        assertFalse(d11League.isValid());

        d11League.setSeason(season());
        assertTrue(d11League.isValid());
    }

    @Test
    public void references() {
        D11League d11League = d11League();
        List<D11MatchDay> d11MatchDays = d11MatchDays();

        d11MatchDays.forEach(d11MatchDay -> d11League.addD11MatchDay(d11MatchDay));
        d11MatchDays.forEach(d11MatchDay -> assertEquals(d11League, d11MatchDay.getD11League()));

        d11MatchDays.forEach(d11MatchDay -> d11League.removeD11MatchDay(d11MatchDay));
        d11MatchDays.forEach(d11MatchDay -> assertEquals(null, d11MatchDay.getD11League()));
    }

    @Test
    public void map() {
        D11League d11League = d11League();
        d11League.setSeason(season());

        ModelMapper modelMapper = new D11RestModelMapper();

        D11LeagueDTO d11LeagueDTO = modelMapper.map(d11League, D11LeagueDTO.class);

        assertEqualsDTO(d11League, d11LeagueDTO);

        D11League mappedD11League = new D11League();

        modelMapper.map(d11LeagueDTO, mappedD11League);

        assertEquals(d11League.getId(), mappedD11League.getId());
        assertEquals(d11League.getName(), mappedD11League.getName());
        assertNull(mappedD11League.getSeason());
    }

}
