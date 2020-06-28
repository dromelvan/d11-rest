package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.team;
import static org.d11.rest.model.D11RestMock.teamTableStats;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.TeamTableStatDTO;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.model.jpa.Team;
import org.d11.rest.model.jpa.TeamTableStat;
import org.d11.rest.repository.TeamTableStatRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class TeamTableStatServiceTests extends RepositoryServiceTests<TeamTableStat, TeamTableStatDTO, TeamTableStatRepository, TeamTableStatService> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new TeamTableStatService(mock(TeamTableStatRepository.class)));

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

    @Test
    public void findByMatchDayId() {
        TeamTableStat teamTableStat = getD11RestEntities().get(0);
        when(getRepositoryService().getJpaRepository().findByMatchDayIdOrderByRankingAsc(teamTableStat.getMatchDay().getId())).thenReturn(Arrays.asList(teamTableStat));

        List<TeamTableStatDTO> result = getRepositoryService().findByMatchDayId(teamTableStat.getMatchDay().getId());
        assertNotNull(result);
        assertEqualsDTO(Arrays.asList(teamTableStat), result);

        result = getRepositoryService().findByMatchDayId(-1);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
