package org.d11.rest.model.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.d11.rest.api.model.Status;
import org.d11.rest.model.jpa.converter.StatusConverter;

@Entity
@Table(name = "matches")
public class Match extends D11RestEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    @NotNull
    private Team homeTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    @NotNull
    private Team awayTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_day_id")
    @NotNull
    private MatchDay matchDay;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    @NotNull
    private Stadium stadium;
    @NotNull
    @Positive
    @Column(name = "whoscored_id")
    private Integer whoScoredId;
    @NotNull
    @PositiveOrZero
    private Integer homeTeamGoals;
    @NotNull
    @PositiveOrZero
    private Integer awayTeamGoals;
    @NotNull
    @PositiveOrZero
    private Integer previousHomeTeamGoals;
    @NotNull
    @PositiveOrZero
    private Integer previousAwayTeamGoals;
    @NotNull
    private LocalDateTime datetime;
    @NotNull
    private String elapsed;
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<PlayerMatchStat> playerMatchStats = new ArrayList<>();
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("time ASC")
    private List<Goal> goals = new ArrayList<>();
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("time ASC")
    private List<Card> cards = new ArrayList<>();
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("time ASC")
    private List<Substitution> substitutions = new ArrayList<>();

    protected Match() {
    }

    public Match(Integer whoScoredId, Integer homeTeamGoals, Integer awayTeamGoals, Integer previousHomeTeamGoals, Integer previousAwayTeamGoals, LocalDateTime datetime, String elapsed, Status status) {
        this.whoScoredId = whoScoredId;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.previousHomeTeamGoals = previousHomeTeamGoals;
        this.previousAwayTeamGoals = previousAwayTeamGoals;
        this.datetime = datetime;
        this.elapsed = elapsed;
        this.status = status;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public MatchDay getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Integer getWhoScoredId() {
        return whoScoredId;
    }

    public void setWhoScoredId(Integer whoScoredId) {
        this.whoScoredId = whoScoredId;
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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
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

    public List<PlayerMatchStat> getPlayerMatchStats() {
        return playerMatchStats;
    }

    public void setPlayerMatchStats(List<PlayerMatchStat> playerMatchStats) {
        this.playerMatchStats = playerMatchStats;
    }

    public void addPlayerMatchStat(PlayerMatchStat playerMatchStat) {
        if (playerMatchStat != null && !this.playerMatchStats.contains(playerMatchStat)) {
            this.playerMatchStats.add(playerMatchStat);
            playerMatchStat.setMatch(this);
        }
    }

    public void removePlayerMatchStat(PlayerMatchStat playerMatchStat) {
        if (playerMatchStat != null && this.playerMatchStats.contains(playerMatchStat)) {
            this.playerMatchStats.remove(playerMatchStat);
            playerMatchStat.setMatch(null);
        }
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public void addGoal(Goal goal) {
        if (goal != null && !this.goals.contains(goal)) {
            this.goals.add(goal);
            goal.setMatch(this);
        }
    }

    public void removeGoal(Goal goal) {
        if (goal != null && this.goals.contains(goal)) {
            this.goals.remove(goal);
            goal.setMatch(null);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        if (card != null && !this.cards.contains(card)) {
            this.cards.add(card);
            card.setMatch(this);
        }
    }

    public void removeCard(Card card) {
        if (card != null && this.cards.contains(card)) {
            this.cards.remove(card);
            card.setMatch(null);
        }
    }

    public List<Substitution> getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(List<Substitution> substitutions) {
        this.substitutions = substitutions;
    }

    public void addSubstitution(Substitution substitution) {
        if (substitution != null && !this.substitutions.contains(substitution)) {
            this.substitutions.add(substitution);
            substitution.setMatch(this);
        }
    }

    public void removeSubstitution(Substitution substitution) {
        if (substitution != null && this.substitutions.contains(substitution)) {
            this.substitutions.remove(substitution);
            substitution.setMatch(null);
        }
    }

}
