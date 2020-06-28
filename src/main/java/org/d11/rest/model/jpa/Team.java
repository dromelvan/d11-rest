package org.d11.rest.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams")
public class Team extends D11RestEntity {

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    @NotNull
    private Stadium stadium;
    @NotNull
    @Positive
    @Column(name = "whoscored_id")
    private Integer whoScoredId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String shortName;
    @NotEmpty
    @Size(min = 3, max = 3)
    private String code;
    @NotNull
    private String nickname;
    @Min(1800)
    @Max(2050)
    @NotNull
    private Integer established;
    @NotNull
    private String motto;
    @NotNull
    @Pattern(regexp = "\\#\\d{6}")
    private String colour;
    @NotNull
    private Boolean dummy;

    protected Team() {
    }

    public Team(Integer whoScoredId, String name, String shortName, String code, String nickname, Integer established, String motto, String colour, Boolean dummy) {
        this.whoScoredId = whoScoredId;
        this.name = name;
        this.shortName = shortName;
        this.code = code;
        this.nickname = nickname;
        this.established = established;
        this.motto = motto;
        this.colour = colour;
        this.dummy = dummy;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getEstablished() {
        return established;
    }

    public void setEstablished(Integer established) {
        this.established = established;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Boolean isDummy() {
        return dummy;
    }

    public void setDummy(Boolean dummy) {
        this.dummy = dummy;
    }

}
