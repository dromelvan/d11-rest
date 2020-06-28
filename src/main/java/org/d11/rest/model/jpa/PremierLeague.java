package org.d11.rest.model.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "premier_leagues")
public class PremierLeague extends League {

    @OneToMany(mappedBy = "premierLeague", cascade = CascadeType.ALL)
    @OrderBy("matchDayNumber ASC")
    private List<MatchDay> matchDays = new ArrayList<>();

    protected PremierLeague() {
    }

    public PremierLeague(String name) {
        super(name);
    }

    public List<MatchDay> getMatchDays() {
        return matchDays;
    }

    public void setMatchDays(List<MatchDay> matchDays) {
        this.matchDays = matchDays;
    }

    public void addMatchDay(MatchDay matchDay) {
        if (matchDay != null && !this.matchDays.contains(matchDay)) {
            this.matchDays.add(matchDay);
            matchDay.setPremierLeague(this);
        }
    }

    public void removeMatchDay(MatchDay matchDay) {
        if (matchDay != null && this.matchDays.contains(matchDay)) {
            this.matchDays.remove(matchDay);
            matchDay.setPremierLeague(null);
        }
    }

}
