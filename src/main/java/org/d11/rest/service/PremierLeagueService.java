package org.d11.rest.service;

import java.util.List;
import java.util.Optional;

import org.d11.rest.api.model.PremierLeagueDTO;
import org.d11.rest.model.jpa.PremierLeague;
import org.d11.rest.repository.EntityId;
import org.d11.rest.repository.PremierLeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremierLeagueService extends RepositoryService<PremierLeague, PremierLeagueDTO, PremierLeagueRepository> {

    @Autowired
    public PremierLeagueService(PremierLeagueRepository premierLeagueRepository) {
        super(premierLeagueRepository);
    }

    @Override
    public List<PremierLeagueDTO> findAll() {
        return map(getJpaRepository().findByOrderBySeasonDateDesc());
    }

    public PremierLeagueDTO findCurrentPremierLeague() {
        Optional<EntityId> optional = getJpaRepository().findFirstByOrderBySeasonDateDesc();
        if (optional.isPresent()) {
            EntityId entityId = optional.get();
            return find(getJpaRepository().findById(entityId.getId()));
        }
        return find(Optional.empty());
    }

    @Override
    protected Class<PremierLeagueDTO> getDTOClass() {
        return PremierLeagueDTO.class;
    }

}
