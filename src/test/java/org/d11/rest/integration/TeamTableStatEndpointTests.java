package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.TeamTableStatDTO;
import org.d11.rest.controller.TeamTableStatController;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.TeamTableStat;
import org.d11.rest.repository.TeamTableStatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class TeamTableStatEndpointTests extends SeasonMockEndpointTests<TeamTableStat, TeamTableStatDTO, TeamTableStatController> {

    @Override
    protected TeamTableStatDTO readValue(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<TeamTableStatDTO>() {
        });
    }

    @Override
    protected List<TeamTableStatDTO> readValues(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<List<TeamTableStatDTO>>() {
        });
    }

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        setD11RestEntities(getRepository(TeamTableStatRepository.class).findAll());
    }

    @Test
    public void findAll() throws Exception {
        super.findAll(Endpoint.TEAM_TABLE_STATS);
    }

    @Test
    public void findAllIds() throws Exception {
        super.findAllIds(Endpoint.TEAM_TABLE_STATS_IDS);
    }

    @Test
    public void findById() throws Exception {
        super.findById(Endpoint.TEAM_TABLE_STAT);
    }

    @Test
    public void findByMatchDayId() throws Exception {
        MatchDay matchDay = getD11RestEntities().get(0).getMatchDay();
        List<TeamTableStat> teamTableStats = new ArrayList<>();
        for (TeamTableStat teamTableStat : getD11RestEntities()) {
            if (teamTableStat.getMatchDay().equals(matchDay)) {
                teamTableStats.add(teamTableStat);
            }
        }
        Collections.sort(teamTableStats, new Comparator<TeamTableStat>() {
            @Override
            public int compare(TeamTableStat teamTableStat, TeamTableStat teamTableStat2) {
                return teamTableStat.getRanking() - teamTableStat2.getRanking();
            }

        });
        TeamTableStat teamTableStat = teamTableStats.get(0);

        assertOk(get(Endpoint.TEAM_TABLE_STAT_BY_MATCH_DAY_ID, teamTableStat.getId()));
        assertOk(get(Endpoint.TEAM_TABLE_STAT_BY_MATCH_DAY_ID, teamTableStat.getId()), token(user()));

        String token = token(administrator());

        MvcResult mvcResult = assertOk(get(Endpoint.TEAM_TABLE_STAT_BY_MATCH_DAY_ID, teamTableStat.getMatchDay().getId()), token);

        List<TeamTableStatDTO> result = readValues(mvcResult);

        assertEqualsDTO(teamTableStats, result);
    }

}
