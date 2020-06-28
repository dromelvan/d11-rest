package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.D11TeamTableStat;

public interface D11TeamTableStatRepository extends D11RestRepository<D11TeamTableStat> {

    public List<D11TeamTableStat> findByD11MatchDayIdOrderByRankingAsc(Long d11MatchDayId);

}
