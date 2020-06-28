package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.d11Matches;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11MatchDTO;
import org.d11.rest.model.jpa.*;
import org.d11.rest.model.jpa.projection.D11TeamRemainingPlayerCount;
import org.d11.rest.service.D11MatchService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11MatchControllerTests extends RepositoryControllerTests<D11Match, D11MatchDTO, D11MatchController> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new D11MatchController(mock(D11MatchService.class)));

        D11League d11League = d11League();
        List<D11Match> d11Matches = d11Matches();
        D11MatchDay d11MatchDay = d11MatchDay();
        d11League.addD11MatchDay(d11MatchDay);

        d11Matches.forEach(d11Match -> d11Match.setHomeD11Team(d11Team()));
        d11Matches.forEach(d11Match -> d11Match.setAwayD11Team(d11Team()));
        d11Matches.forEach(d11Match -> d11Match.setD11MatchDay(d11MatchDay));

        setD11RestEntities(d11Matches);

        super.beforeAll();
    }

    @Test
    @Override
    public void findById() {
        super.findById();

        for(D11Match d11Match : getD11RestEntities()) {
            ResponseEntity<D11MatchDTO> response = getRepositoryController().findById(d11Match.getId());
            D11MatchDTO d11MatchDTO = response.getBody();

            assertNotNull(d11MatchDTO.getRemainingPlayerCount());
            assertEquals(d11Match.getHomeD11Team().getId(), d11MatchDTO.getRemainingPlayerCount().get(d11Match.getHomeD11Team().getId()));
            assertEquals(d11Match.getAwayD11Team().getId(), d11MatchDTO.getRemainingPlayerCount().get(d11Match.getAwayD11Team().getId()));
        }
    }

    @Override
    protected D11MatchDTO map(D11Match d11Match) {
        D11MatchDTO d11MatchDTO = super.map(d11Match, getDTOClass());

        List<D11TeamRemainingPlayerCount> d11TeamRemainingPlayerCounts = new ArrayList<>();
        d11TeamRemainingPlayerCounts.add(new D11TeamRemainingPlayerCount() {
            @Override
            public Long getRemainingPlayerCount() {
                return d11MatchDTO.getHomeD11Team().getId();
            }

            @Override
            public Long getD11TeamId() {
                return d11MatchDTO.getHomeD11Team().getId();
            }
        });
        d11TeamRemainingPlayerCounts.add(new D11TeamRemainingPlayerCount() {
            @Override
            public Long getRemainingPlayerCount() {
                return d11MatchDTO.getAwayD11Team().getId();
            }

            @Override
            public Long getD11TeamId() {
                return d11MatchDTO.getAwayD11Team().getId();
            }
        });

        d11TeamRemainingPlayerCounts.forEach(d11TeamRemainingPlayerCount -> d11MatchDTO.getRemainingPlayerCount().put(d11TeamRemainingPlayerCount.getD11TeamId(), d11TeamRemainingPlayerCount.getRemainingPlayerCount()));

        return d11MatchDTO;
    }

    @Override
    protected Class<D11MatchDTO> getDTOClass() {
        return D11MatchDTO.class;
    }

}
