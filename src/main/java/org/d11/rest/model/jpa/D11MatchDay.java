package org.d11.rest.model.jpa;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "d11_match_days")
public class D11MatchDay extends D11RestEntity {

    @ManyToOne
    @JoinColumn(name = "d11_league_id")
    @NotNull
    private D11League d11League;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_day_id")
    @NotNull
    private MatchDay matchDay;
    @NotNull
    private LocalDate date;
    @NotNull
    @Min(1)
    @Max(38)
    private Integer matchDayNumber;

    @OneToMany(mappedBy = "d11MatchDay", cascade = CascadeType.ALL)
    @OrderBy("date")
    private List<D11Match> d11Matches = new ArrayList<>();

    protected D11MatchDay() {
    }

    public D11MatchDay(LocalDate date, Integer matchDayNumber) {
        this.date = date;
        this.matchDayNumber = matchDayNumber;
    }

    public D11League getD11League() {
        return d11League;
    }

    public void setD11League(D11League d11League) {
        this.d11League = d11League;
    }

    public MatchDay getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMatchDayNumber() {
        return matchDayNumber;
    }

    public void setMatchDayNumber(Integer matchDayNumber) {
        this.matchDayNumber = matchDayNumber;
    }

    public List<D11Match> getD11Matches() {
        return d11Matches;
    }

    public void setD11Matches(List<D11Match> d11Matches) {
        this.d11Matches = d11Matches;
    }

    public void addD11Match(D11Match d11Match) {
        if(d11Match != null && !this.d11Matches.contains(d11Match)) {
            this.d11Matches.add(d11Match);
            d11Match.setD11MatchDay(this);
        }
    }

    public void removeD11Match(D11Match d11Match) {
        if(d11Match != null && this.d11Matches.contains(d11Match)) {
            this.d11Matches.remove(d11Match);
            d11Match.setD11MatchDay(null);
        }
    }

}
