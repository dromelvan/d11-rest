package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "stadia")
public class Stadium extends D11RestEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    private String city;
    @Positive
    @NotNull
    private Integer capacity;
    @Min(1800)
    @Max(2050)
    @NotNull
    private Integer opened;

    protected Stadium() {
    }

    public Stadium(String name, String city, Integer capacity, Integer opened) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
        this.opened = opened;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOpened() {
        return opened;
    }

    public void setOpened(Integer opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
