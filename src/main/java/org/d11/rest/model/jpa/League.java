package org.d11.rest.model.jpa;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class League extends D11RestEntity {

    @OneToOne
    @JoinColumn(name = "season_id")
    @NotNull
    private Season season;
    @NotEmpty
    private String name;

    protected League() {
    }

    public League(String name) {
        this.name = name;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
