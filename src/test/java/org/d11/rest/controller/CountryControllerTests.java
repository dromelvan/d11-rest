package org.d11.rest.controller;

import static org.d11.rest.model.D11RestMock.countries;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.CountryDTO;
import org.d11.rest.model.jpa.Country;
import org.d11.rest.service.CountryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class CountryControllerTests extends RepositoryControllerTests<Country, CountryDTO, CountryController>{

	@BeforeAll
	public void beforeAll() {
		setRepositoryController(new CountryController(mock(CountryService.class)));
		
		List<Country> countries= countries();
		setD11RestEntities(countries);
		
		super.beforeAll();
	}
	
	@Override
	protected Class<CountryDTO> getDTOClass() {
		return CountryDTO.class;
	}
	
}
