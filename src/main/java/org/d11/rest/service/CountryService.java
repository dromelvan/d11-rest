package org.d11.rest.service;

import java.util.List;

import org.d11.rest.api.model.CountryDTO;
import org.d11.rest.model.jpa.Country;
import org.d11.rest.model.jpa.projection.EntityId;
import org.d11.rest.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends RepositoryService<Country, CountryDTO, CountryRepository> {

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        super(countryRepository);
    }

    @Override
    public List<CountryDTO> findAll() {
        return map(getJpaRepository().findByOrderByNameAsc());
    }

    @Override
    protected List<EntityId> findAllEntityIds() {
        return getJpaRepository().findIdByOrderByNameAsc();
    }

    @Override
    protected Class<CountryDTO> getDTOClass() {
        return CountryDTO.class;
    }

}
