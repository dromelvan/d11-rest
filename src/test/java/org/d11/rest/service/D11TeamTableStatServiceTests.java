package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.d11Team;
import static org.d11.rest.model.D11RestMock.d11TeamTableStats;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11TeamTableStatDTO;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.D11TeamTableStatRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11TeamTableStatServiceTests extends RepositoryServiceTests<D11TeamTableStat, D11TeamTableStatDTO, D11TeamTableStatRepository, D11TeamTableStatService> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new D11TeamTableStatService(mock(D11TeamTableStatRepository.class)));

        List<D11TeamTableStat> d11TeamTableStats = d11TeamTableStats();
        for (D11TeamTableStat d11TeamTableStat : d11TeamTableStats) {
            D11Team d11Team = d11Team();
            d11TeamTableStat.setD11Team(d11Team);
            D11League d11League = d11League();
            d11TeamTableStat.setD11League(d11League);
            D11MatchDay d11MatchDay = d11MatchDay();
            d11TeamTableStat.setD11MatchDay(d11MatchDay);
        }

        setD11RestEntities(d11TeamTableStats);

        super.beforeAll();
    }

    @Test
    public void findByD11MatchDayId() {
        D11TeamTableStat d11TeamTableStat = getD11RestEntities().get(0);
        when(getRepositoryService().getJpaRepository().findByD11MatchDayIdOrderByRankingAsc(d11TeamTableStat.getD11MatchDay().getId())).thenReturn(Arrays.asList(d11TeamTableStat));

        List<D11TeamTableStatDTO> result = getRepositoryService().findByD11MatchDayId(d11TeamTableStat.getD11MatchDay().getId());
        assertNotNull(result);
        assertEqualsDTO(Arrays.asList(d11TeamTableStat), result);

        result = getRepositoryService().findByD11MatchDayId(-1);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
