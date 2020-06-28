package org.d11.rest.service;

import java.time.LocalDate;
import java.util.Optional;

import org.d11.rest.api.model.MatchDayDTO;
import org.d11.rest.model.jpa.MatchDay;
import org.d11.rest.model.jpa.projection.EntityId;
import org.d11.rest.repository.MatchDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchDayService extends RepositoryService<MatchDay, MatchDayDTO, MatchDayRepository> {

    @Autowired
    public MatchDayService(MatchDayRepository matchDayRepository) {
        super(matchDayRepository);
    }

    public MatchDayDTO findCurrentMatchDay() {
        LocalDate localDate = LocalDate.now();
        Optional<EntityId> optional = getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(localDate);
        if (optional.isPresent()) {
            EntityId entityId = optional.get();
            return find(getJpaRepository().findById(entityId.getId()));
        }
        return find(Optional.empty());
    }

    @Override
    protected Class<MatchDayDTO> getDTOClass() {
        return MatchDayDTO.class;
    }

}
