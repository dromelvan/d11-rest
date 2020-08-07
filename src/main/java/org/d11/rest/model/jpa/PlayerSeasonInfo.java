package org.d11.rest.model.jpa;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "player_season_infos")
public class PlayerSeasonInfo extends D11RestEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @NotNull
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @NotNull
    private Season season;    
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
    @PositiveOrZero
    private Integer value;
    
    protected PlayerSeasonInfo() {}
    
    public PlayerSeasonInfo(Integer value) {
        this.value = value;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
}
