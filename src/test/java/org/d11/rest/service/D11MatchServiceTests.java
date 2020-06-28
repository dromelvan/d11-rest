package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.d11Matches;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11MatchDTO;
import org.d11.rest.model.jpa.*;
import org.d11.rest.model.jpa.projection.D11TeamRemainingPlayerCount;
import org.d11.rest.repository.D11MatchRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11MatchServiceTests extends RepositoryServiceTests<D11Match, D11MatchDTO, D11MatchRepository, D11MatchService> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new D11MatchService(mock(D11MatchRepository.class)));

        D11League d11League = d11League();
        List<D11Match> d11Matches = d11Matches();
        D11MatchDay d11MatchDay = d11MatchDay();
        d11League.addD11MatchDay(d11MatchDay);

        d11Matches.forEach(d11Match -> d11Match.setHomeD11Team(d11Team(1)));
        d11Matches.forEach(d11Match -> d11Match.setAwayD11Team(d11Team(2)));
        d11Matches.forEach(d11Match -> d11Match.setD11MatchDay(d11MatchDay));

        setD11RestEntities(d11Matches);

        super.beforeAll();
    }

    @Test
    @Override
    public void findById() {
        D11Match d11Match = getD11RestEntities().get(0);
        List<D11TeamRemainingPlayerCount> d11TeamRemainingPlayerCounts = new ArrayList<>();
        d11TeamRemainingPlayerCounts.add(mock(D11TeamRemainingPlayerCount.class));
        d11TeamRemainingPlayerCounts.add(mock(D11TeamRemainingPlayerCount.class));

        when(d11TeamRemainingPlayerCounts.get(0).getD11TeamId()).thenReturn(d11Match.getHomeD11Team().getId());
        when(d11TeamRemainingPlayerCounts.get(0).getRemainingPlayerCount()).thenReturn((long) 2);
        when(d11TeamRemainingPlayerCounts.get(1).getD11TeamId()).thenReturn(d11Match.getAwayD11Team().getId());
        when(d11TeamRemainingPlayerCounts.get(1).getRemainingPlayerCount()).thenReturn((long) 3);

        when(getRepositoryService().getJpaRepository().findD11TeamRemainingPlayerCountByD11MatchId(d11Match.getId())).thenReturn(d11TeamRemainingPlayerCounts);

        super.findById();

        D11MatchDTO d11MatchDTO = getRepositoryService().findById(d11Match.getId());

        assertNotNull(d11MatchDTO.getRemainingPlayerCount());
        assertEquals(2, d11MatchDTO.getRemainingPlayerCount().get(d11Match.getHomeD11Team().getId()));
        assertEquals(3, d11MatchDTO.getRemainingPlayerCount().get(d11Match.getAwayD11Team().getId()));
    }

}
