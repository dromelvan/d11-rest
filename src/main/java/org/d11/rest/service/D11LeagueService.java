package org.d11.rest.service;

import java.util.List;
import java.util.Optional;

import org.d11.rest.api.model.D11LeagueDTO;
import org.d11.rest.model.jpa.D11League;
import org.d11.rest.repository.D11LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class D11LeagueService extends RepositoryService<D11League, D11LeagueDTO, D11LeagueRepository> {

    @Autowired
    public D11LeagueService(D11LeagueRepository d11LeagueRepository) {
        super(d11LeagueRepository);
    }

    @Override
    public List<D11LeagueDTO> findAll() {
        return map(getJpaRepository().findByOrderBySeasonDateDesc());
    }

    public D11LeagueDTO findCurrentD11League() {
        Optional<D11League> optional = getJpaRepository().findFirstByOrderBySeasonDateDesc();
        return find(optional);
    }

    @Override
    protected Class<D11LeagueDTO> getDTOClass() {
        return D11LeagueDTO.class;
    }

}
