package org.d11.rest.model.jpa;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.d11.rest.api.model.Status;
import org.d11.rest.model.jpa.converter.StatusConverter;
import org.d11.rest.model.jpa.validation.YearInterval;

@Entity
@Table(name = "seasons")
public class Season extends D11RestEntity {

    @YearInterval
    private String name;
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;
    @NotNull
    private LocalDate date;
    private boolean legacy;

    @OneToOne(mappedBy = "season", cascade = CascadeType.ALL)
    private PremierLeague premierLeague;
    @OneToOne(mappedBy = "season", cascade = CascadeType.ALL)
    private D11League d11League;

    protected Season() {
    }

    public Season(String name, LocalDate date, boolean legacy) {
        this.name = name;
        this.date = date;
        this.legacy = legacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public void setLegacy(boolean legacy) {
        this.legacy = legacy;
    }

    public PremierLeague getPremierLeague() {
        return premierLeague;
    }

    public void setPremierLeague(PremierLeague premierLeague) {
        if(premierLeague != null) {
            premierLeague.setSeason(this);
        } else if(this.premierLeague != null) {
            this.premierLeague.setSeason(null);
        }
        this.premierLeague = premierLeague;
    }

    public D11League getD11League() {
        return d11League;
    }

    public void setD11League(D11League d11League) {
        if(d11League != null) {
            d11League.setSeason(this);
        } else if(this.d11League != null) {
            this.d11League.setSeason(null);
        }
        this.d11League = d11League;
    }

    public String getShortName() {
        if(this.name != null) {
            return this.name.substring(2, 5) + this.name.substring(7);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
