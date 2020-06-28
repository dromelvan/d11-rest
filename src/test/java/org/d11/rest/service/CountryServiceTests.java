package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.countries;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.d11.rest.Tags;
import org.d11.rest.api.model.CountryDTO;
import org.d11.rest.model.jpa.Country;
import org.d11.rest.repository.CountryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class CountryServiceTests extends RepositoryServiceTests<Country, CountryDTO, CountryRepository, CountryService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new CountryService(mock(CountryRepository.class)));
		setD11RestEntities(countries());
		
		super.beforeAll();
		when(getRepositoryService().getJpaRepository().findByOrderByNameAsc()).thenReturn(getD11RestEntities());
	}	
	
}
