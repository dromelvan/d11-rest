package org.d11.rest.service;

import java.util.List;

import org.d11.rest.api.model.TeamDTO;
import org.d11.rest.model.jpa.Team;
import org.d11.rest.model.jpa.projection.EntityId;
import org.d11.rest.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends RepositoryService<Team, TeamDTO, TeamRepository> {

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        super(teamRepository);
    }

    @Override
    public List<TeamDTO> findAll() {
        return map(getJpaRepository().findByOrderByNameAsc());
    }

    @Override
    protected List<EntityId> findAllEntityIds() {
        return getJpaRepository().findIdByOrderByNameAsc();
    }

    @Override
    protected Class<TeamDTO> getDTOClass() {
        return TeamDTO.class;
    }

}
