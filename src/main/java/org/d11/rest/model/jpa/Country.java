package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "countries")
public class Country extends D11RestEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    @Size(min = 2, max = 2)
    private String iso;

    protected Country() {
    }

    public Country(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

}
