package org.d11.rest.service;

import org.d11.rest.api.model.GoalDTO;
import org.d11.rest.model.jpa.Goal;
import org.d11.rest.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService extends RepositoryService<Goal, GoalDTO, GoalRepository> {

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        super(goalRepository);
    }

    @Override
    protected Class<GoalDTO> getDTOClass() {
        return GoalDTO.class;
    }

}
