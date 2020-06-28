package org.d11.rest.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.d11.rest.model.jpa.Match;
import org.modelmapper.AbstractConverter;

public class MatchesByDateConverter extends AbstractConverter<List<Match>, Map<LocalDate, List<Long>>> {

    @Override
    public Map<LocalDate, List<Long>> convert(List<Match> matches) {
        Map<LocalDate, List<Long>> matchesByDate = new LinkedHashMap<>();
        for (Match match : matches) {
            LocalDate localDate = match.getDatetime().toLocalDate();
            List<Long> ids = matchesByDate.get(localDate);
            if (ids == null) {
                ids = new ArrayList<Long>();
                matchesByDate.put(localDate, ids);
            }
            ids.add(match.getId());
        }
        return matchesByDate;
    }

}
