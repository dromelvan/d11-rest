package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.TeamTableStat;

public interface TeamTableStatRepository extends D11RestRepository<TeamTableStat> {

    public List<TeamTableStat> findByMatchDayIdOrderByRankingAsc(Long matchDayId);

}
