package org.d11.rest.service;

import java.time.LocalDate;
import java.util.Optional;

import org.d11.rest.api.model.D11MatchDayDTO;
import org.d11.rest.model.jpa.D11MatchDay;
import org.d11.rest.model.jpa.projection.EntityId;
import org.d11.rest.repository.D11MatchDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class D11MatchDayService extends RepositoryService<D11MatchDay, D11MatchDayDTO, D11MatchDayRepository> {

    @Autowired
    public D11MatchDayService(D11MatchDayRepository d11MatchDayRepository) {
        super(d11MatchDayRepository);
    }

    public D11MatchDayDTO findCurrentD11MatchDay() {
        LocalDate localDate = LocalDate.now();
        Optional<EntityId> optional = getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(localDate);
        if (optional.isPresent()) {
            EntityId entityId = optional.get();
            return find(getJpaRepository().findById(entityId.getId()));
        }
        return find(Optional.empty());
    }

    @Override
    protected Class<D11MatchDayDTO> getDTOClass() {
        return D11MatchDayDTO.class;
    }

}
