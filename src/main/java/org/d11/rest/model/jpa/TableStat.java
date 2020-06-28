package org.d11.rest.model.jpa;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@MappedSuperclass
public class TableStat extends D11RestEntity {

    @NotNull
    @PositiveOrZero
    private Integer matchesPlayed;
    @NotNull
    @PositiveOrZero
    private Integer matchesWon;
    @NotNull
    @PositiveOrZero
    private Integer matchesDrawn;
    @NotNull
    @PositiveOrZero
    private Integer matchesLost;
    @NotNull
    @PositiveOrZero
    private Integer goalsFor;
    @NotNull
    @PositiveOrZero
    private Integer goalsAgainst;
    @NotNull
    private Integer goalDifference;
    @NotNull
    @PositiveOrZero
    private Integer points;
    @NotNull
    @Positive
    private Integer ranking;

    @NotNull
    @PositiveOrZero
    private Integer homeMatchesPlayed;
    @NotNull
    @PositiveOrZero
    private Integer homeMatchesWon;
    @NotNull
    @PositiveOrZero
    private Integer homeMatchesDrawn;
    @NotNull
    @PositiveOrZero
    private Integer homeMatchesLost;
    @NotNull
    @PositiveOrZero
    private Integer homeGoalsFor;
    @NotNull
    @PositiveOrZero
    private Integer homeGoalsAgainst;
    @NotNull
    private Integer homeGoalDifference;
    @NotNull
    @PositiveOrZero
    private Integer homePoints;
    @NotNull
    @Positive
    private Integer homeRanking;

    @NotNull
    @PositiveOrZero
    private Integer awayMatchesPlayed;
    @NotNull
    @PositiveOrZero
    private Integer awayMatchesWon;
    @NotNull
    @PositiveOrZero
    private Integer awayMatchesDrawn;
    @NotNull
    @PositiveOrZero
    private Integer awayMatchesLost;
    @NotNull
    @PositiveOrZero
    private Integer awayGoalsFor;
    @NotNull
    @PositiveOrZero
    private Integer awayGoalsAgainst;
    @NotNull
    private Integer awayGoalDifference;
    @NotNull
    @PositiveOrZero
    private Integer awayPoints;
    @NotNull
    @Positive
    private Integer awayRanking;

    @NotNull
    @PositiveOrZero
    private Integer formPoints;
    @NotNull
    @Positive
    private Integer previousRanking;

    protected TableStat() {
    }

    public TableStat(Integer matchesPlayed, Integer matchesWon, Integer matchesDrawn, Integer matchesLost, Integer goalsFor, Integer goalsAgainst, Integer goalDifference, Integer points, Integer ranking, Integer homeMatchesPlayed, Integer homeMatchesWon, Integer homeMatchesDrawn, Integer homeMatchesLost, Integer homeGoalsFor, Integer homeGoalsAgainst, Integer homeGoalDifference, Integer homePoints, Integer homeRanking, Integer awayMatchesPlayed, Integer awayMatchesWon, Integer awayMatchesDrawn,
            Integer awayMatchesLost, Integer awayGoalsFor, Integer awayGoalsAgainst, Integer awayGoalDifference, Integer awayPoints, Integer awayRanking, Integer formPoints, Integer previousRanking) {
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesDrawn = matchesDrawn;
        this.matchesLost = matchesLost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
        this.ranking = ranking;
        this.homeMatchesPlayed = homeMatchesPlayed;
        this.homeMatchesWon = homeMatchesWon;
        this.homeMatchesDrawn = homeMatchesDrawn;
        this.homeMatchesLost = homeMatchesLost;
        this.homeGoalsFor = homeGoalsFor;
        this.homeGoalsAgainst = homeGoalsAgainst;
        this.homeGoalDifference = homeGoalDifference;
        this.homePoints = homePoints;
        this.homeRanking = homeRanking;
        this.awayMatchesPlayed = awayMatchesPlayed;
        this.awayMatchesWon = awayMatchesWon;
        this.awayMatchesDrawn = awayMatchesDrawn;
        this.awayMatchesLost = awayMatchesLost;
        this.awayGoalsFor = awayGoalsFor;
        this.awayGoalsAgainst = awayGoalsAgainst;
        this.awayGoalDifference = awayGoalDifference;
        this.awayPoints = awayPoints;
        this.awayRanking = awayRanking;
        this.formPoints = formPoints;
        this.previousRanking = previousRanking;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(Integer matchesWon) {
        this.matchesWon = matchesWon;
    }

    public Integer getMatchesDrawn() {
        return matchesDrawn;
    }

    public void setMatchesDrawn(Integer matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    public Integer getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(Integer matchesLost) {
        this.matchesLost = matchesLost;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getHomeMatchesPlayed() {
        return homeMatchesPlayed;
    }

    public void setHomeMatchesPlayed(Integer homeMatchesPlayed) {
        this.homeMatchesPlayed = homeMatchesPlayed;
    }

    public Integer getHomeMatchesWon() {
        return homeMatchesWon;
    }

    public void setHomeMatchesWon(Integer homeMatchesWon) {
        this.homeMatchesWon = homeMatchesWon;
    }

    public Integer getHomeMatchesDrawn() {
        return homeMatchesDrawn;
    }

    public void setHomeMatchesDrawn(Integer homeMatchesDrawn) {
        this.homeMatchesDrawn = homeMatchesDrawn;
    }

    public Integer getHomeMatchesLost() {
        return homeMatchesLost;
    }

    public void setHomeMatchesLost(Integer homeMatchesLost) {
        this.homeMatchesLost = homeMatchesLost;
    }

    public Integer getHomeGoalsFor() {
        return homeGoalsFor;
    }

    public void setHomeGoalsFor(Integer homeGoalsFor) {
        this.homeGoalsFor = homeGoalsFor;
    }

    public Integer getHomeGoalsAgainst() {
        return homeGoalsAgainst;
    }

    public void setHomeGoalsAgainst(Integer homeGoalsAgainst) {
        this.homeGoalsAgainst = homeGoalsAgainst;
    }

    public Integer getHomeGoalDifference() {
        return homeGoalDifference;
    }

    public void setHomeGoalDifference(Integer homeGoalDifference) {
        this.homeGoalDifference = homeGoalDifference;
    }

    public Integer getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(Integer homePoints) {
        this.homePoints = homePoints;
    }

    public Integer getHomeRanking() {
        return homeRanking;
    }

    public void setHomeRanking(Integer homeRanking) {
        this.homeRanking = homeRanking;
    }

    public Integer getAwayMatchesPlayed() {
        return awayMatchesPlayed;
    }

    public void setAwayMatchesPlayed(Integer awayMatchesPlayed) {
        this.awayMatchesPlayed = awayMatchesPlayed;
    }

    public Integer getAwayMatchesWon() {
        return awayMatchesWon;
    }

    public void setAwayMatchesWon(Integer awayMatchesWon) {
        this.awayMatchesWon = awayMatchesWon;
    }

    public Integer getAwayMatchesDrawn() {
        return awayMatchesDrawn;
    }

    public void setAwayMatchesDrawn(Integer awayMatchesDrawn) {
        this.awayMatchesDrawn = awayMatchesDrawn;
    }

    public Integer getAwayMatchesLost() {
        return awayMatchesLost;
    }

    public void setAwayMatchesLost(Integer awayMatchesLost) {
        this.awayMatchesLost = awayMatchesLost;
    }

    public Integer getAwayGoalsFor() {
        return awayGoalsFor;
    }

    public void setAwayGoalsFor(Integer awayGoalsFor) {
        this.awayGoalsFor = awayGoalsFor;
    }

    public Integer getAwayGoalsAgainst() {
        return awayGoalsAgainst;
    }

    public void setAwayGoalsAgainst(Integer awayGoalsAgainst) {
        this.awayGoalsAgainst = awayGoalsAgainst;
    }

    public Integer getAwayGoalDifference() {
        return awayGoalDifference;
    }

    public void setAwayGoalDifference(Integer awayGoalDifference) {
        this.awayGoalDifference = awayGoalDifference;
    }

    public Integer getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Integer awayPoints) {
        this.awayPoints = awayPoints;
    }

    public Integer getAwayRanking() {
        return awayRanking;
    }

    public void setAwayRanking(Integer awayRanking) {
        this.awayRanking = awayRanking;
    }

    public Integer getFormPoints() {
        return formPoints;
    }

    public void setFormPoints(Integer formPoints) {
        this.formPoints = formPoints;
    }

    public Integer getPreviousRanking() {
        return previousRanking;
    }

    public void setPreviousRanking(Integer previousRanking) {
        this.previousRanking = previousRanking;
    }

}
