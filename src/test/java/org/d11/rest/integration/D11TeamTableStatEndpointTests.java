package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11TeamTableStatDTO;
import org.d11.rest.controller.D11TeamTableStatController;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.D11TeamTableStatRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class D11TeamTableStatEndpointTests extends SeasonMockEndpointTests<D11TeamTableStat, D11TeamTableStatDTO, D11TeamTableStatController> {

    @Override
    protected D11TeamTableStatDTO readValue(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<D11TeamTableStatDTO>() {});
    }

    @Override
    protected List<D11TeamTableStatDTO> readValues(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<List<D11TeamTableStatDTO>>() {});
    }

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        setD11RestEntities(getRepository(D11TeamTableStatRepository.class).findAll());
    }

    @Test
    public void findAll() throws Exception {
        super.findAll(Endpoint.D11_TEAM_TABLE_STATS);
    }

    @Test
    public void findAllIds() throws Exception {
        super.findAllIds(Endpoint.D11_TEAM_TABLE_STATS_IDS);
    }

    @Test
    public void findById() throws Exception {
        super.findById(Endpoint.D11_TEAM_TABLE_STAT);
    }

    @Test
    public void findByD11MatchDayId() throws Exception {
        D11MatchDay d11MatchDay = getD11RestEntities().get(0).getD11MatchDay();
        List<D11TeamTableStat> d11TeamTableStats = new ArrayList<>();
        for (D11TeamTableStat d11TeamTableStat : getD11RestEntities()) {
            if (d11TeamTableStat.getD11MatchDay().equals(d11MatchDay)) {
                d11TeamTableStats.add(d11TeamTableStat);
            }
        }
        Collections.sort(d11TeamTableStats, new Comparator<D11TeamTableStat>() {
            @Override
            public int compare(D11TeamTableStat d11TeamTableStat, D11TeamTableStat d11TeamTableStat2) {
                return d11TeamTableStat.getRanking() - d11TeamTableStat2.getRanking();
            }

        });
        D11TeamTableStat d11TeamTableStat = d11TeamTableStats.get(0);

        assertOk(get(Endpoint.D11_TEAM_TABLE_STAT_BY_D11_MATCH_DAY_ID, d11TeamTableStat.getId()));
        assertOk(get(Endpoint.D11_TEAM_TABLE_STAT_BY_D11_MATCH_DAY_ID, d11TeamTableStat.getId()), token(user()));

        String token = token(administrator());

        MvcResult mvcResult = assertOk(get(Endpoint.D11_TEAM_TABLE_STAT_BY_D11_MATCH_DAY_ID, d11TeamTableStat.getD11MatchDay().getId()), token);

        List<D11TeamTableStatDTO> result = readValues(mvcResult);

        assertEqualsDTO(d11TeamTableStats, result);
    }

}
