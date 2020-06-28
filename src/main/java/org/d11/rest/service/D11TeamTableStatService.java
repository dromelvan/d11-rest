package org.d11.rest.service;

import java.util.List;

import org.d11.rest.api.model.D11TeamTableStatDTO;
import org.d11.rest.model.jpa.D11TeamTableStat;
import org.d11.rest.repository.D11TeamTableStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class D11TeamTableStatService extends RepositoryService<D11TeamTableStat, D11TeamTableStatDTO, D11TeamTableStatRepository> {

    @Autowired
    public D11TeamTableStatService(D11TeamTableStatRepository d11TeamTableStatRepository) {
        super(d11TeamTableStatRepository);
    }

    public List<D11TeamTableStatDTO> findByD11MatchDayId(long d11MatchDayId) {
        List<D11TeamTableStat> d11TeamTableStats = getJpaRepository().findByD11MatchDayIdOrderByRankingAsc(d11MatchDayId);
        return map(d11TeamTableStats);
    }

    @Override
    protected Class<D11TeamTableStatDTO> getDTOClass() {
        return D11TeamTableStatDTO.class;
    }

}
