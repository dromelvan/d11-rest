package org.d11.rest.service;

import java.util.Optional;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.Match;
import org.d11.rest.repository.MatchRepository;
import org.d11.rest.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService extends RepositoryService<Match, MatchDTO, MatchRepository> {

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        super(matchRepository);
    }

    public MatchMatchEventsDTO findMatchDetailsById(int id) {
        return findMatchEventsById(id);
    }

    public MatchMatchEventsDTO findMatchEventsById(long id) {
        Optional<Match> optional = getJpaRepository().findById(id);
        if (optional.isPresent()) {
            Match match = optional.get();
            // TODO: This is really bad but if we try to use an entity graph to load these
            // we get the multiple bags error. Fix this properly later.
            match.getGoals().size();
            match.getCards().size();
            match.getSubstitutions().size();
            return map(match, MatchMatchEventsDTO.class);
        }
        throw new NotFoundException();
    }

    @Override
    protected Class<MatchDTO> getDTOClass() {
        return MatchDTO.class;
    }

}
