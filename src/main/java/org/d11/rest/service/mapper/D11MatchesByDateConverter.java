package org.d11.rest.service.mapper;

import java.time.LocalDate;
import java.util.*;

import org.d11.rest.model.jpa.D11Match;
import org.modelmapper.AbstractConverter;

public class D11MatchesByDateConverter extends AbstractConverter<List<D11Match>, Map<LocalDate, List<Long>>> {

    @Override
    public Map<LocalDate, List<Long>> convert(List<D11Match> d11Matches) {
        Map<LocalDate, List<Long>> d11MatchesByDate = new LinkedHashMap<>();
        for(D11Match d11Match : d11Matches) {
            LocalDate localDate = d11Match.getDate();
            List<Long> ids = d11MatchesByDate.get(localDate);
            if(ids == null) {
                ids = new ArrayList<Long>();
                d11MatchesByDate.put(localDate, ids);
            }
            ids.add(d11Match.getId());
        }
        return d11MatchesByDate;
    }

}
