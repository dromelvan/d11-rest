package org.d11.rest.model.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.google.common.collect.ComparisonChain;

@Entity
@Table(name = "d11_team_table_stats")
public class D11TeamTableStat extends TableStat implements Comparable<D11TeamTableStat> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    private D11Team d11Team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d11_league_id")
    @NotNull
    private D11League d11League;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d11_match_day_id")
    @NotNull
    private D11MatchDay d11MatchDay;

    public D11TeamTableStat() {
        super();
    }

    public D11TeamTableStat(Integer matchesPlayed, Integer matchesWon, Integer matchesDrawn, Integer matchesLost, Integer goalsFor, Integer goalsAgainst, Integer goalDifference, Integer points, Integer ranking, 
                         Integer homeMatchesPlayed, Integer homeMatchesWon, Integer homeMatchesDrawn, Integer homeMatchesLost, Integer homeGoalsFor, Integer homeGoalsAgainst, Integer homeGoalDifference, Integer homePoints, Integer homeRanking, 
                         Integer awayMatchesPlayed, Integer awayMatchesWon, Integer awayMatchesDrawn, Integer awayMatchesLost, Integer awayGoalsFor, Integer awayGoalsAgainst, Integer awayGoalDifference, Integer awayPoints, Integer awayRanking, 
                         Integer formPoints, Integer previousRanking) {
        super(matchesPlayed, matchesWon, matchesDrawn, matchesLost, goalsFor, goalsAgainst, goalDifference, points, ranking, 
              homeMatchesPlayed, homeMatchesWon, homeMatchesDrawn, homeMatchesLost, homeGoalsFor, homeGoalsAgainst, homeGoalDifference, homePoints, homeRanking, 
              awayMatchesPlayed, awayMatchesWon, awayMatchesDrawn, awayMatchesLost, awayGoalsFor, awayGoalsAgainst, awayGoalDifference, awayPoints, awayRanking, 
              formPoints, previousRanking);
    }    
    
    public D11Team getD11Team() {
        return d11Team;
    }

    public void setD11Team(D11Team d11Team) {
        this.d11Team = d11Team;
    }

    public D11League getD11League() {
        return d11League;
    }

    public void setD11League(D11League d11League) {
        this.d11League = d11League;
    }

    public D11MatchDay getD11MatchDay() {
        return d11MatchDay;
    }

    public void setD11MatchDay(D11MatchDay d11MatchDay) {
        this.d11MatchDay = d11MatchDay;
    }

    @Override
    public int compareTo(D11TeamTableStat d11TeamTableStat) {
        return ComparisonChain.start()
                .compare(d11TeamTableStat.getPoints(), getPoints())
                .compare(d11TeamTableStat.getGoalsFor(), getGoalsFor())
                .compare(d11TeamTableStat.getGoalsAgainst(), getGoalsAgainst())
                .compare(getD11Team().getName(), d11TeamTableStat.getD11Team().getName())
                .result();
    }

}
