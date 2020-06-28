package org.d11.rest.service;

import org.d11.rest.api.model.D11TeamDTO;
import org.d11.rest.model.jpa.D11Team;
import org.d11.rest.repository.D11TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class D11TeamService extends RepositoryService<D11Team, D11TeamDTO, D11TeamRepository> {

    @Autowired
    public D11TeamService(D11TeamRepository d11TeamRepository) {
        super(d11TeamRepository);
    }

    @Override
    protected Class<D11TeamDTO> getDTOClass() {
        return D11TeamDTO.class;
    }

}
