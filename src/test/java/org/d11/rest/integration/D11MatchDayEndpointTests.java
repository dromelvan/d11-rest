package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.D11MatchDayDTO;
import org.d11.rest.controller.D11MatchDayController;
import org.d11.rest.model.jpa.D11MatchDay;
import org.d11.rest.repository.D11MatchDayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class D11MatchDayEndpointTests extends SeasonMockEndpointTests<D11MatchDay, D11MatchDayDTO, D11MatchDayController> {

    @Override
    protected D11MatchDayDTO readValue(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<D11MatchDayDTO>() {});
    }
    
    @Override
    protected List<D11MatchDayDTO> readValues(MvcResult mvcResult) throws Exception {
        return readValue(mvcResult, new TypeReference<List<D11MatchDayDTO>>() {});
    }
    
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        setD11RestEntities(getRepository(D11MatchDayRepository.class).findAll());
    }
    
    @Test
    public void findAll() throws Exception {
        super.findAll(Endpoint.D11_MATCH_DAYS);
    }

    @Test
    public void findAllIds() throws Exception {
        super.findAllIds(Endpoint.D11_MATCH_DAYS_IDS);
    }
    
    @Test
    public void findById() throws Exception {
        super.findById(Endpoint.D11_MATCH_DAY);
    }
    
    @Test
    public void findCurrentMatchDay() throws Exception {
        assertOk(get(Endpoint.D11_MATCH_DAY_CURRENT));
        assertOk(get(Endpoint.D11_MATCH_DAY_CURRENT), token(user()));
        
        MvcResult mvcResult = assertOk(get(Endpoint.D11_MATCH_DAY_CURRENT), token(administrator()));
        D11MatchDayDTO d11MatchDayDTO = readValue(mvcResult);
        
        assertEqualsDTO(getD11RestEntities().get(0), d11MatchDayDTO);
    }
    
}
