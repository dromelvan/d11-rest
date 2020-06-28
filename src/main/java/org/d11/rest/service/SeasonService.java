package org.d11.rest.service;

import java.util.List;
import java.util.Optional;

import org.d11.rest.api.model.SeasonDTO;
import org.d11.rest.model.jpa.Season;
import org.d11.rest.model.jpa.projection.EntityId;
import org.d11.rest.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonService extends RepositoryService<Season, SeasonDTO, SeasonRepository> {

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        super(seasonRepository);
    }

    @Override
    public List<SeasonDTO> findAll() {
        return map(getJpaRepository().findByOrderByDateDesc());
    }

    public SeasonDTO findCurrentSeason() {
        Optional<Season> optional = getJpaRepository().findFirstByOrderByDateDesc();
        return find(optional);
    }

    @Override
    protected List<EntityId> findAllEntityIds() {
        return getJpaRepository().findIdByOrderByDateDesc();
    }

    @Override
    protected Class<SeasonDTO> getDTOClass() {
        return SeasonDTO.class;
    }

}
