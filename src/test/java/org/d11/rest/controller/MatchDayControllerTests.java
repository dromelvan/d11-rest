package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11MatchDay;
import static org.d11.rest.model.D11RestMock.matchDays;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.MatchDayDTO;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.service.MatchDayService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class MatchDayControllerTests extends RepositoryControllerTests<MatchDay, MatchDayDTO, MatchDayController> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new MatchDayController(mock(MatchDayService.class)));

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

        when(getRepositoryController().getRepositoryService().findCurrentMatchDay()).thenReturn(map(getD11RestEntities().get(0)));
    }

    @Test
    public void findCurrentMatchDay() {
        MatchDay matchDay = getD11RestEntities().get(0);

        ResponseEntity<MatchDayDTO> responseEntity = getRepositoryController().findCurrentMatchDay();
        MatchDayDTO matchDayDTO = responseEntity.getBody();

        assertEqualsDTO(matchDay, matchDayDTO);
    }

    @Override
    protected Class<MatchDayDTO> getDTOClass() {
        return MatchDayDTO.class;
    }

}
