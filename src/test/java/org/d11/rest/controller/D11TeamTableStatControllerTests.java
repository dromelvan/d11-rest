package org.d11.rest.controller;

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
import org.d11.rest.service.D11TeamTableStatService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11TeamTableStatControllerTests extends RepositoryControllerTests<D11TeamTableStat, D11TeamTableStatDTO, D11TeamTableStatController> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new D11TeamTableStatController(mock(D11TeamTableStatService.class)));

        List<D11TeamTableStat> d11TeamTableStats = d11TeamTableStats();
        for(D11TeamTableStat d11TeamTableStat : d11TeamTableStats) {
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

    @Override
    protected Class<D11TeamTableStatDTO> getDTOClass() {
        return D11TeamTableStatDTO.class;
    }

    @Test
    public void findByD11MatchDayId() {
        D11TeamTableStat d11TeamTableStat = getD11RestEntities().get(0);
        when(getRepositoryController().getRepositoryService().findByD11MatchDayId(d11TeamTableStat.getD11MatchDay().getId())).thenReturn(Arrays.asList(map(d11TeamTableStat, D11TeamTableStatDTO.class)));
        when(getRepositoryController().getRepositoryService().findByD11MatchDayId(-1)).thenReturn(new ArrayList<D11TeamTableStatDTO>());

        ResponseEntity<List<D11TeamTableStatDTO>> responseEntity = getRepositoryController().findByD11MatchDayId(d11TeamTableStat.getD11MatchDay().getId());

        List<D11TeamTableStatDTO> result = responseEntity.getBody();
        assertNotNull(result);
        assertEqualsDTO(Arrays.asList(d11TeamTableStat), result);

        responseEntity = getRepositoryController().findByD11MatchDayId(-1);

        result = responseEntity.getBody();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
