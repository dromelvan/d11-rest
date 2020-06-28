package org.d11.rest.service;

import org.d11.rest.api.model.StadiumDTO;
import org.d11.rest.model.jpa.Stadium;
import org.d11.rest.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StadiumService extends RepositoryService<Stadium, StadiumDTO, StadiumRepository> {

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        super(stadiumRepository);
    }

    @Override
    protected Class<StadiumDTO> getDTOClass() {
        return StadiumDTO.class;
    }

}
