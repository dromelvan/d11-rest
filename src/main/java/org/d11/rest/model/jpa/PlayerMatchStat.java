package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "player_match_stats")
public class PlayerMatchStat extends PlayerStat {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @NotNull
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @NotNull
    private Match match;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @NotNull
    private Team team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    private D11Team d11Team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    @NotNull
    private Position position;
    @NotNull
    @NotBlank
    private String playedPosition;
    @NotNull
    @Min(0)
    @Max(2)
    private Integer lineup;
    @NotNull
    @Min(0)
    @Max(90)
    private Integer substitutionOnTime;
    @NotNull
    @Min(0)
    @Max(90)
    private Integer substitutionOffTime;
    @NotNull
    @Min(0)
    @Max(90)
    private Integer yellowCardTime;
    @NotNull
    @Min(0)
    @Max(90)
    private Integer redCardTime;
    @NotNull
    private Boolean manOfTheMatch;
    @NotNull
    private Boolean sharedManOfTheMatch;

    protected PlayerMatchStat() {
    }

    public PlayerMatchStat(String playedPosition, Integer lineup, Integer substitutionOnTime, Integer substitutionOffTime, Integer yellowCardTime, Integer redCardTime, Boolean manOfTheMatch, Boolean sharedManOfTheMatch, Integer goals, Integer goalAssists, Integer ownGoals, Integer goalsConceded, Integer rating, Integer points) {
        super(goals, goalAssists, ownGoals, goalsConceded, rating, points);
        this.playedPosition = playedPosition;
        this.lineup = lineup;
        this.substitutionOnTime = substitutionOnTime;
        this.substitutionOffTime = substitutionOffTime;
        this.yellowCardTime = yellowCardTime;
        this.redCardTime = redCardTime;
        this.manOfTheMatch = manOfTheMatch;
        this.sharedManOfTheMatch = sharedManOfTheMatch;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public D11Team getD11Team() {
        return d11Team;
    }

    public void setD11Team(D11Team d11Team) {
        this.d11Team = d11Team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPlayedPosition() {
        return playedPosition;
    }

    public void setPlayedPosition(String playedPosition) {
        this.playedPosition = playedPosition;
    }

    public Integer getLineup() {
        return lineup;
    }

    public void setLineup(Integer lineup) {
        this.lineup = lineup;
    }

    public Integer getSubstitutionOnTime() {
        return substitutionOnTime;
    }

    public void setSubstitutionOnTime(Integer substitutionOnTime) {
        this.substitutionOnTime = substitutionOnTime;
    }

    public Integer getSubstitutionOffTime() {
        return substitutionOffTime;
    }

    public void setSubstitutionOffTime(Integer substitutionOffTime) {
        this.substitutionOffTime = substitutionOffTime;
    }

    public Integer getYellowCardTime() {
        return yellowCardTime;
    }

    public void setYellowCardTime(Integer yellowCardTime) {
        this.yellowCardTime = yellowCardTime;
    }

    public Integer getRedCardTime() {
        return redCardTime;
    }

    public void setRedCardTime(Integer redCardTime) {
        this.redCardTime = redCardTime;
    }

    public Boolean isManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(Boolean manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    public Boolean isSharedManOfTheMatch() {
        return sharedManOfTheMatch;
    }

    public void setSharedManOfTheMatch(Boolean sharedManOfTheMatch) {
        this.sharedManOfTheMatch = sharedManOfTheMatch;
    }

}
