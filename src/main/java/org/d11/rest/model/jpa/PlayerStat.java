package org.d11.rest.model.jpa;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@MappedSuperclass
public class PlayerStat extends D11RestEntity {

    @NotNull
    @PositiveOrZero
    private Integer goals;
    @NotNull
    @PositiveOrZero
    private Integer goalAssists;
    @NotNull
    @PositiveOrZero
    private Integer ownGoals;
    @NotNull
    @PositiveOrZero
    private Integer goalsConceded;
    @NotNull
    @Min(0)
    @Max(1000)
    private Integer rating;
    @NotNull
    @PositiveOrZero
    private Integer points;

    public PlayerStat() {
    }

    public PlayerStat(Integer goals, Integer goalAssists, Integer ownGoals, Integer goalsConceded, Integer rating, Integer points) {
        this.goals = goals;
        this.goalAssists = goalAssists;
        this.ownGoals = ownGoals;
        this.goalsConceded = goalsConceded;
        this.rating = rating;
        this.points = points;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getGoalAssists() {
        return goalAssists;
    }

    public void setGoalAssists(Integer goalAssists) {
        this.goalAssists = goalAssists;
    }

    public Integer getOwnGoals() {
        return ownGoals;
    }

    public void setOwnGoals(Integer ownGoals) {
        this.ownGoals = ownGoals;
    }

    public Integer getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(Integer goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
