package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.countries;

import java.util.Collections;
import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.CountryDTO;
import org.d11.rest.controller.CountryController;
import org.d11.rest.model.jpa.Country;
import org.d11.rest.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class CountryEndpointTests extends RepositoryIntegrationTests<Country, CountryDTO, CountryController> {

	@Autowired
	public CountryEndpointTests(CountryRepository countryRepository) {
		super(countryRepository);
	}

	@Override
	protected CountryDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<CountryDTO>() {});
	}
	
	@Override
	protected List<CountryDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<CountryDTO>>() {});
	}
	
	@BeforeEach
	public void beforeAll() {
		List<Country> countries = countries();
		Collections.reverse(countries);

		CountryRepository countryRepository = getRepository(CountryRepository.class);
		countryRepository.saveAll(countries);
		
		setD11RestEntities(getRepository(CountryRepository.class).findByOrderByNameAsc());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.COUNTRIES);
	}
	
	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.COUNTRIES_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.COUNTRY);
	}
	
}
