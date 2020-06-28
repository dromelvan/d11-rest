package org.d11.rest.service;

import org.d11.rest.api.model.PositionDTO;
import org.d11.rest.model.jpa.Position;
import org.d11.rest.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService extends RepositoryService<Position, PositionDTO, PositionRepository> {

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        super(positionRepository);
    }

    @Override
    protected Class<PositionDTO> getDTOClass() {
        return PositionDTO.class;
    }

}
