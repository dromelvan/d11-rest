package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.entityId;
import static org.d11.rest.model.D11RestMock.matchDays;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.d11.rest.Tags;
import org.d11.rest.api.model.MatchDayDTO;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.repository.MatchDayRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class MatchDayServiceTests extends RepositoryServiceTests<MatchDay, MatchDayDTO, MatchDayRepository, MatchDayService> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new MatchDayService(mock(MatchDayRepository.class)));

        Season season = season();
        PremierLeague premierLeague = premierLeague();
        season.setPremierLeague(premierLeague);

        List<MatchDay> matchDays = matchDays();
        for(MatchDay matchDay : matchDays) {
            premierLeague.addMatchDay(matchDay);
            matchDay.setD11MatchDay(d11MatchDay());
        }
        setD11RestEntities(matchDays);

        super.beforeAll();

        when(getRepositoryService().getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(getD11RestEntities().get(0).getDate())).thenReturn(Optional.of(entityId(getD11RestEntities().get(0).getId())));
    }

    @Test
    public void findCurrentMatchDay() {
        MatchDayDTO matchDayDTO = getRepositoryService().findCurrentMatchDay();
        assertEqualsDTO(getD11RestEntities().get(0), matchDayDTO);
    }

}
