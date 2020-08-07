package org.d11.rest.model.jpa;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "player_season_stats")
public class PlayerSeasonStat extends PlayerStat {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @NotNull
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @NotNull
    private Season season;    
    
    @NotNull
    @PositiveOrZero
    private Integer cleanSheets;
    @NotNull
    @PositiveOrZero
    private Integer yellowCards;
    @NotNull
    @PositiveOrZero
    private Integer redCards;
    @NotNull
    @PositiveOrZero
    private Integer manOfTheMatch;
    @NotNull
    @PositiveOrZero
    private Integer sharedManOfTheMatch;
    @NotNull
    @PositiveOrZero
    private Integer gamesStarted;
    @NotNull
    @PositiveOrZero
    private Integer gamesSubstitute;
    @NotNull
    @PositiveOrZero
    private Integer gamesDidNotParticipate;
    @NotNull
    @PositiveOrZero
    private Integer substitutionsOn;
    @NotNull
    @PositiveOrZero
    private Integer substitutionsOff;
    @NotNull
    @PositiveOrZero
    private Integer minutesPlayed;
    @NotNull
    @PositiveOrZero
    private Integer formPoints;
    @NotNull
    @PositiveOrZero
    private Integer pointsPerAppearance;
    @NotNull
    @PositiveOrZero
    private Integer ranking;

    protected PlayerSeasonStat() {}

    public PlayerSeasonStat(Integer goals, Integer goalAssists, Integer ownGoals, Integer goalsConceded, Integer rating, Integer points, 
                            Integer cleanSheets, Integer yellowCards, Integer redCards, Integer manOfTheMatch, Integer sharedManOfTheMatch, Integer gamesStarted, Integer gamesSubstitute, Integer gamesDidNotParticipate, 
                            Integer substitutionsOn, Integer substitutionsOff, Integer minutesPlayed, Integer formPoints, Integer pointsPerAppearance, Integer ranking) {
        super(goals, goalAssists, ownGoals, goalsConceded, rating, points);
        this.cleanSheets = cleanSheets;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.manOfTheMatch = manOfTheMatch;
        this.sharedManOfTheMatch = sharedManOfTheMatch;
        this.gamesStarted = gamesStarted;
        this.gamesSubstitute = gamesSubstitute;
        this.gamesDidNotParticipate = gamesDidNotParticipate;
        this.substitutionsOn = substitutionsOn;
        this.substitutionsOff = substitutionsOff;
        this.minutesPlayed = minutesPlayed;
        this.formPoints = formPoints;
        this.pointsPerAppearance = pointsPerAppearance;
        this.ranking = ranking;
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

    public Integer getCleanSheets() {
        return cleanSheets;
    }

    public void setCleanSheets(Integer cleanSheets) {
        this.cleanSheets = cleanSheets;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(Integer manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    public Integer getSharedManOfTheMatch() {
        return sharedManOfTheMatch;
    }

    public void setSharedManOfTheMatch(Integer sharedManOfTheMatch) {
        this.sharedManOfTheMatch = sharedManOfTheMatch;
    }

    public Integer getGamesStarted() {
        return gamesStarted;
    }

    public void setGamesStarted(Integer gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public Integer getGamesSubstitute() {
        return gamesSubstitute;
    }

    public void setGamesSubstitute(Integer gamesSubstitute) {
        this.gamesSubstitute = gamesSubstitute;
    }

    public Integer getGamesDidNotParticipate() {
        return gamesDidNotParticipate;
    }

    public void setGamesDidNotParticipate(Integer gamesDidNotParticipate) {
        this.gamesDidNotParticipate = gamesDidNotParticipate;
    }

    public Integer getSubstitutionsOn() {
        return substitutionsOn;
    }

    public void setSubstitutionsOn(Integer substitutionsOn) {
        this.substitutionsOn = substitutionsOn;
    }

    public Integer getSubstitutionsOff() {
        return substitutionsOff;
    }

    public void setSubstitutionsOff(Integer substitutionsOff) {
        this.substitutionsOff = substitutionsOff;
    }

    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public Integer getFormPoints() {
        return formPoints;
    }

    public void setFormPoints(Integer formPoints) {
        this.formPoints = formPoints;
    }

    public Integer getPointsPerAppearance() {
        return pointsPerAppearance;
    }

    public void setPointsPerAppearance(Integer pointsPerAppearance) {
        this.pointsPerAppearance = pointsPerAppearance;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
       
}
