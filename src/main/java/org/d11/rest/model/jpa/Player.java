package org.d11.rest.model.jpa;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.d11.rest.api.util.Parameterizer;

@Entity
@Table(name = "players")
public class Player extends D11RestEntity {

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    private Country country;
    @NotNull
    @Positive
    @Column(name = "whoscored_id")
    private Integer whoScoredId;
    @NotNull
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    @Min(0)
    private Integer height;
    @NotNull
    @Min(0)
    private Integer weight;
    @NotNull
    @NotEmpty
    private String parameterizedName;

    protected Player() {
    }

    public Player(Integer whoScoredId, String firstName, String lastName, LocalDate dateOfBirth, Integer height, Integer weight) {
        this.whoScoredId = whoScoredId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getWhoScoredId() {
        return whoScoredId;
    }

    public void setWhoScoredId(Integer whoScoredId) {
        this.whoScoredId = whoScoredId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return (getFirstName() + " " + getLastName()).trim();
    }

    public String getShortName() {
        if (getFirstName().isEmpty()) {
            return getLastName();
        } else {
            return getLastName() + " " + getFirstName().charAt(0);
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getParameterizedName() {
        return parameterizedName;
    }

    public void setParameterizedName(String parameterizedName) {
        this.parameterizedName = parameterizedName;
    }

    @Override
    @PrePersist
    public void prePersist() {
        updateParameterizedName();
        super.prePersist();
    }

    @Override
    @PreUpdate
    public void preUpdate() {
        updateParameterizedName();
        super.preUpdate();
    }

    private void updateParameterizedName() {
        String parameterizedName = Parameterizer.parameterize(getName());
        setParameterizedName(parameterizedName);
    }
    
}
