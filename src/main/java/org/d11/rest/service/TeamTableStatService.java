package org.d11.rest.service;

import java.util.List;

import org.d11.rest.api.model.TeamTableStatDTO;
import org.d11.rest.model.jpa.TeamTableStat;
import org.d11.rest.repository.TeamTableStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamTableStatService extends RepositoryService<TeamTableStat, TeamTableStatDTO, TeamTableStatRepository> {

    @Autowired
    public TeamTableStatService(TeamTableStatRepository teamTableStatRepository) {
        super(teamTableStatRepository);
    }

    public List<TeamTableStatDTO> findByMatchDayId(long matchDayId) {
        List<TeamTableStat> teamTableStats = getJpaRepository().findByMatchDayIdOrderByRankingAsc(matchDayId);
        return map(teamTableStats);
    }

    @Override
    protected Class<TeamTableStatDTO> getDTOClass() {
        return TeamTableStatDTO.class;
    }

}
