package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDays;
import static org.d11.rest.model.D11RestMock.entityId;
import static org.d11.rest.model.D11RestMock.matchDay;
import static org.d11.rest.model.D11RestMock.season;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.d11.rest.Tags;
import org.d11.rest.api.model.D11MatchDayDTO;
import org.d11.rest.model.jpa.D11League;
import org.d11.rest.model.jpa.D11MatchDay;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.repository.D11MatchDayRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11MatchDayServiceTests extends RepositoryServiceTests<D11MatchDay, D11MatchDayDTO, D11MatchDayRepository, D11MatchDayService> {

    @Override
    @BeforeAll
    public void beforeAll() {
        setRepositoryService(new D11MatchDayService(mock(D11MatchDayRepository.class)));

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

        when(getRepositoryService().getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(getD11RestEntities().get(0).getDate())).thenReturn(Optional.of(entityId(getD11RestEntities().get(0).getId())));
    }

    @Test
    public void findCurrentMatchDay() {
        D11MatchDayDTO d11MatchDayDTO = getRepositoryService().findCurrentD11MatchDay();
        assertEqualsDTO(getD11RestEntities().get(0), d11MatchDayDTO);
    }

}
