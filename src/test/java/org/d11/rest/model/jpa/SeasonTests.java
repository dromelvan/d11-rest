package org.d11.rest.model.jpa;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11Leagues;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.premierLeagues;
import static org.d11.rest.model.D11RestMock.season;
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
public class SeasonTests {

    @Test
    public void isValid() {
        Season season = season();

        assertTrue(season.isValid());

        season.setName("");
        assertFalse(season.isValid());
        season.setName(null);
        assertFalse(season.isValid());
        assertNull(season.getShortName());
        season.setName("INVALID_NAME");
        assertFalse(season.isValid());
        season.setName("1002-1001");
        assertFalse(season.isValid());
        season.setName("1000-1001");
        assertEquals(season.getShortName(), "00-01");

        season.setStatus(null);
        assertFalse(season.isValid());
        season.setStatus(Status.PENDING);

        season.setDate(null);
        assertFalse(season.isValid());
        season.setDate(LocalDate.now());

        assertTrue(season.isValid());
    }

    @Test
    public void references() {
        Season season = season();
        PremierLeague premierLeague = premierLeague();
        D11League d11League = d11League();

        season.setPremierLeague(premierLeague);
        assertEquals(season, premierLeague.getSeason());
        season.setPremierLeague(null);
        assertEquals(null, premierLeague.getSeason());

        season.setD11League(d11League);
        assertEquals(season, d11League.getSeason());
        season.setD11League(null);
        assertEquals(null, d11League.getSeason());
    }

    @Test
    public void map() {
        Season season = season();
        season.setPremierLeague(premierLeagues().get(0));
        season.setD11League(d11Leagues().get(0));

        ModelMapper modelMapper = new D11RestModelMapper();

        SeasonDTO seasonDTO = modelMapper.map(season, SeasonDTO.class);

        assertEqualsDTO(season, seasonDTO);

        Season mappedSeason = new Season();

        modelMapper.map(seasonDTO, mappedSeason);

        assertEquals(season.getId(), mappedSeason.getId());
        assertEquals(season.getName(), mappedSeason.getName());
        assertEquals(season.getDate(), mappedSeason.getDate());
        assertEquals(season.getStatus(), mappedSeason.getStatus());
        assertNull(mappedSeason.getPremierLeague());
        assertNull(mappedSeason.getD11League());
    }

}
