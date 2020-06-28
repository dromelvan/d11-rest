package org.d11.rest.model.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.google.common.collect.ComparisonChain;

@Entity
@Table(name = "team_table_stats")
public class TeamTableStat extends TableStat implements Comparable<TeamTableStat> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @NotNull
    private Team team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "premier_league_id")
    @NotNull
    private PremierLeague premierLeague;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_day_id")
    @NotNull
    private MatchDay matchDay;

    public TeamTableStat() {
        super();
    }

    public TeamTableStat(Integer matchesPlayed, Integer matchesWon, Integer matchesDrawn, Integer matchesLost, Integer goalsFor, Integer goalsAgainst, Integer goalDifference, Integer points, Integer ranking, 
                         Integer homeMatchesPlayed, Integer homeMatchesWon, Integer homeMatchesDrawn, Integer homeMatchesLost, Integer homeGoalsFor, Integer homeGoalsAgainst, Integer homeGoalDifference, Integer homePoints, Integer homeRanking, 
                         Integer awayMatchesPlayed, Integer awayMatchesWon, Integer awayMatchesDrawn, Integer awayMatchesLost, Integer awayGoalsFor, Integer awayGoalsAgainst, Integer awayGoalDifference, Integer awayPoints, Integer awayRanking, 
                         Integer formPoints, Integer previousRanking) {
        super(matchesPlayed, matchesWon, matchesDrawn, matchesLost, goalsFor, goalsAgainst, goalDifference, points, ranking, 
              homeMatchesPlayed, homeMatchesWon, homeMatchesDrawn, homeMatchesLost, homeGoalsFor, homeGoalsAgainst, homeGoalDifference, homePoints, homeRanking, 
              awayMatchesPlayed, awayMatchesWon, awayMatchesDrawn, awayMatchesLost, awayGoalsFor, awayGoalsAgainst, awayGoalDifference, awayPoints, awayRanking, 
              formPoints, previousRanking);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PremierLeague getPremierLeague() {
        return premierLeague;
    }

    public void setPremierLeague(PremierLeague premierLeague) {
        this.premierLeague = premierLeague;
    }

    public MatchDay getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    @Override
    public int compareTo(TeamTableStat teamTableStat) {
        return ComparisonChain.start()
                .compare(teamTableStat.getPoints(), getPoints())
                .compare(teamTableStat.getGoalDifference(), getGoalDifference())
                .compare(teamTableStat.getGoalsFor(), getGoalsFor())
                .compare(getTeam().getName(), teamTableStat.getTeam().getName())
                .result();
    }

}
