package org.d11.rest.model.jpa;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class MatchEvent extends D11RestEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @NotNull
    private Match match;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @NotNull
    private Team team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @NotNull
    private Player player;
    @NotNull
    @Min(0)
    @Max(90)
    private Integer time;
    @NotNull
    @Min(0)
    private Integer addedTime;

    protected MatchEvent() {
    }

    public MatchEvent(Integer time, Integer addedTime) {
        this.time = time;
        this.addedTime = addedTime;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Integer addedTime) {
        this.addedTime = addedTime;
    }

}
