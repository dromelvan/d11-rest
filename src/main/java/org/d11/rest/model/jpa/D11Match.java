package org.d11.rest.model.jpa;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.d11.rest.api.model.Status;
import org.d11.rest.model.jpa.converter.StatusConverter;

@Entity
@Table(name = "d11_matches")
public class D11Match extends D11RestEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_d11_team_id")
    @NotNull
    private D11Team homeD11Team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_d11_team_id")
    @NotNull
    private D11Team awayD11Team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d11_match_day_id")
    @NotNull
    private D11MatchDay d11MatchDay;
    @NotNull
    @PositiveOrZero
    private Integer homeTeamGoals;
    @NotNull
    @PositiveOrZero
    private Integer awayTeamGoals;
    @NotNull
    private Integer homeTeamPoints;
    @NotNull
    private Integer awayTeamPoints;
    @NotNull
    @PositiveOrZero
    private Integer previousHomeTeamGoals;
    @NotNull
    @PositiveOrZero
    private Integer previousAwayTeamGoals;
    @NotNull
    private Integer previousHomeTeamPoints;
    @NotNull
    private Integer previousAwayTeamPoints;
    @NotNull
    private LocalDate date;
    @NotNull
    private String elapsed;
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    protected D11Match() {
    }

    public D11Match(Integer homeTeamGoals, Integer awayTeamGoals, Integer homeTeamPoints, Integer awayTeamPoints, Integer previousHomeTeamGoals, Integer previousAwayTeamGoals, Integer previousHomeTeamPoints, Integer previousAwayTeamPoints, LocalDate date, String elapsed, Status status) {
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.homeTeamPoints = homeTeamPoints;
        this.awayTeamPoints = awayTeamPoints;
        this.previousHomeTeamGoals = previousHomeTeamGoals;
        this.previousAwayTeamGoals = previousAwayTeamGoals;
        this.previousHomeTeamPoints = previousHomeTeamPoints;
        this.previousAwayTeamPoints = previousAwayTeamPoints;
        this.date = date;
        this.elapsed = elapsed;
        this.status = status;
    }

    public D11Team getHomeD11Team() {
        return homeD11Team;
    }

    public void setHomeD11Team(D11Team homeD11Team) {
        this.homeD11Team = homeD11Team;
    }

    public D11Team getAwayD11Team() {
        return awayD11Team;
    }

    public void setAwayD11Team(D11Team awayD11Team) {
        this.awayD11Team = awayD11Team;
    }

    public D11MatchDay getD11MatchDay() {
        return d11MatchDay;
    }

    public void setD11MatchDay(D11MatchDay d11MatchDay) {
        this.d11MatchDay = d11MatchDay;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Integer getHomeTeamPoints() {
        return homeTeamPoints;
    }

    public void setHomeTeamPoints(Integer homeTeamPoints) {
        this.homeTeamPoints = homeTeamPoints;
    }

    public Integer getAwayTeamPoints() {
        return awayTeamPoints;
    }

    public void setAwayTeamPoints(Integer awayTeamPoints) {
        this.awayTeamPoints = awayTeamPoints;
    }

    public Integer getPreviousHomeTeamGoals() {
        return previousHomeTeamGoals;
    }

    public void setPreviousHomeTeamGoals(Integer previousHomeTeamGoals) {
        this.previousHomeTeamGoals = previousHomeTeamGoals;
    }

    public Integer getPreviousAwayTeamGoals() {
        return previousAwayTeamGoals;
    }

    public void setPreviousAwayTeamGoals(Integer previousAwayTeamGoals) {
        this.previousAwayTeamGoals = previousAwayTeamGoals;
    }

    public Integer getPreviousHomeTeamPoints() {
        return previousHomeTeamPoints;
    }

    public void setPreviousHomeTeamPoints(Integer previousHomeTeamPoints) {
        this.previousHomeTeamPoints = previousHomeTeamPoints;
    }

    public Integer getPreviousAwayTeamPoints() {
        return previousAwayTeamPoints;
    }

    public void setPreviousAwayTeamPoints(Integer previousAwayTeamPoints) {
        this.previousAwayTeamPoints = previousAwayTeamPoints;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
