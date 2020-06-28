package org.d11.rest.model.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.d11.rest.api.model.Status;
import org.d11.rest.model.jpa.converter.StatusConverter;

@Entity
@Table(name = "match_days")
public class MatchDay extends D11RestEntity {

    @ManyToOne
    @JoinColumn(name = "premier_league_id")
    @NotNull
    private PremierLeague premierLeague;
    @NotNull
    private LocalDate date;
    @NotNull
    @Min(1)
    @Max(38)
    private Integer matchDayNumber;
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    @OneToOne(mappedBy = "matchDay")
    private D11MatchDay d11MatchDay;
    @OneToMany(mappedBy = "matchDay", cascade = CascadeType.ALL)
    @OrderBy("datetime ASC")
    private List<Match> matches = new ArrayList<>();

    protected MatchDay() {
    }

    public MatchDay(LocalDate date, Integer matchDayNumber, Status status) {
        this.date = date;
        this.matchDayNumber = matchDayNumber;
        this.status = status;
    }

    public PremierLeague getPremierLeague() {
        return premierLeague;
    }

    public void setPremierLeague(PremierLeague premierLeague) {
        this.premierLeague = premierLeague;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public D11MatchDay getD11MatchDay() {
        return d11MatchDay;
    }

    public void setD11MatchDay(D11MatchDay d11MatchDay) {
        this.d11MatchDay = d11MatchDay;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        if(match != null && !this.matches.contains(match)) {
            this.matches.add(match);
            match.setMatchDay(this);
        }
    }

    public void removeMatch(Match match) {
        if(match != null && this.matches.contains(match)) {
            this.matches.remove(match);
            match.setMatchDay(null);
        }
    }

    @Override
    public String toString() {
        return this.premierLeague + " Match Day " + this.matchDayNumber;
    }
}
