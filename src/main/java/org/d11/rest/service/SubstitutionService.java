package org.d11.rest.service;

import org.d11.rest.api.model.SubstitutionDTO;
import org.d11.rest.model.jpa.Substitution;
import org.d11.rest.repository.SubstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubstitutionService extends RepositoryService<Substitution, SubstitutionDTO, SubstitutionRepository> {

    @Autowired
    public SubstitutionService(SubstitutionRepository substitutionRepository) {
        super(substitutionRepository);
    }

    @Override
    protected Class<SubstitutionDTO> getDTOClass() {
        return SubstitutionDTO.class;
    }

}
