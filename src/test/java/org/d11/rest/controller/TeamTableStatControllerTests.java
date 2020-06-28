package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.team;
import static org.d11.rest.model.D11RestMock.teamTableStats;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.TeamTableStatDTO;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.model.jpa.Team;
import org.d11.rest.model.jpa.TeamTableStat;
import org.d11.rest.service.TeamTableStatService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class TeamTableStatControllerTests extends RepositoryControllerTests<TeamTableStat, TeamTableStatDTO, TeamTableStatController> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new TeamTableStatController(mock(TeamTableStatService.class)));

        List<TeamTableStat> teamTableStats = teamTableStats();
        for (TeamTableStat teamTableStat : teamTableStats) {
            Team team = team();
            teamTableStat.setTeam(team);
            PremierLeague premierLeague = premierLeague();
            teamTableStat.setPremierLeague(premierLeague);
            MatchDay matchDay = matchDay();
            teamTableStat.setMatchDay(matchDay);
        }

        setD11RestEntities(teamTableStats);

        super.beforeAll();
    }

    @Override
    protected Class<TeamTableStatDTO> getDTOClass() {
        return TeamTableStatDTO.class;
    }

    @Test
    public void findByMatchDayId() {
        TeamTableStat teamTableStat = getD11RestEntities().get(0);
        when(getRepositoryController().getRepositoryService().findByMatchDayId(teamTableStat.getMatchDay().getId())).thenReturn(Arrays.asList(map(teamTableStat, TeamTableStatDTO.class)));
        when(getRepositoryController().getRepositoryService().findByMatchDayId(-1)).thenReturn(new ArrayList<TeamTableStatDTO>());

        ResponseEntity<List<TeamTableStatDTO>> responseEntity = getRepositoryController().findByMatchDayId(teamTableStat.getMatchDay().getId());

        List<TeamTableStatDTO> result = responseEntity.getBody();
        assertNotNull(result);
        assertEqualsDTO(Arrays.asList(teamTableStat), result);

        responseEntity = getRepositoryController().findByMatchDayId(-1);

        result = responseEntity.getBody();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
