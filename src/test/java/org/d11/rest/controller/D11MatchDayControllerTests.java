package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDays;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11MatchDayDTO;
import org.d11.rest.model.jpa.D11League;
import org.d11.rest.model.jpa.D11MatchDay;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.service.D11MatchDayService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.ResponseEntity;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11MatchDayControllerTests extends RepositoryControllerTests<D11MatchDay, D11MatchDayDTO, D11MatchDayController> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryController(new D11MatchDayController(mock(D11MatchDayService.class)));

        Season season = season();
        D11League d11League = d11League();
        season.setD11League(d11League);

        List<D11MatchDay> d11MatchDays = d11MatchDays();
        for (D11MatchDay d11MatchDay : d11MatchDays) {
            d11MatchDay.setD11League(d11League);
            d11MatchDay.setMatchDay(matchDay());
        }

        setD11RestEntities(d11MatchDays);

        super.beforeAll();

        when(getRepositoryController().getRepositoryService().findCurrentD11MatchDay()).thenReturn(map(getD11RestEntities().get(0)));
    }

    @Test
    public void findCurrentD11MatchDay() {
        D11MatchDay d11MatchDay = getD11RestEntities().get(0);

        ResponseEntity<D11MatchDayDTO> responseEntity = getRepositoryController().findCurrentD11MatchDay();
        D11MatchDayDTO d11MatchDayDTO = responseEntity.getBody();

        assertEqualsDTO(d11MatchDay, d11MatchDayDTO);
    }

    @Override
    protected Class<D11MatchDayDTO> getDTOClass() {
        return D11MatchDayDTO.class;
    }

}
