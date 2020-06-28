package org.d11.rest.service;

import java.util.List;

import org.d11.rest.api.model.D11MatchDTO;
import org.d11.rest.model.jpa.D11Match;
import org.d11.rest.model.jpa.projection.D11TeamRemainingPlayerCount;
import org.d11.rest.repository.D11MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class D11MatchService extends RepositoryService<D11Match, D11MatchDTO, D11MatchRepository> {

    @Autowired
    public D11MatchService(D11MatchRepository d11MatchRepository) {
        super(d11MatchRepository);
    }

    @Override
    public D11MatchDTO findById(long id) {
        D11MatchDTO d11MatchDTO = super.findById(id);
        List<D11TeamRemainingPlayerCount> d11TeamRemainingPlayerCounts = getJpaRepository().findD11TeamRemainingPlayerCountByD11MatchId(id);
        // TODO: Figure out an elegant way to do this with ModelMapper, if there is one.
        d11TeamRemainingPlayerCounts.forEach(d11TeamRemainingPlayerCount -> d11MatchDTO.getRemainingPlayerCount().put(d11TeamRemainingPlayerCount.getD11TeamId(), d11TeamRemainingPlayerCount.getRemainingPlayerCount()));
        return d11MatchDTO;
    }

    @Override
    protected Class<D11MatchDTO> getDTOClass() {
        return D11MatchDTO.class;
    }

}
