package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "d11_teams")
public class D11Team extends D11RestEntity {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NotNull
    private User owner;
    @NotEmpty
    private String name;
    @NotEmpty
    private String shortName;
    @NotEmpty
    @Size(min = 3, max = 3)
    private String code;
    @NotNull
    private Boolean dummy;

    protected D11Team() {
    }

    public D11Team(String name, String shortName, String code, Boolean dummy) {
        this.name = name;
        this.shortName = shortName;
        this.code = code;
        this.dummy = dummy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Boolean isDummy() {
        return dummy;
    }

    public void setDummy(Boolean dummy) {
        this.dummy = dummy;
    }

}
