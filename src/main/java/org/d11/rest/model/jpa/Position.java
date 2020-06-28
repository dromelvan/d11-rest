package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "positions")
public class Position extends D11RestEntity {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Size(min = 1, max = 2)
    private String code;
    @NotNull
    private Boolean defender;
    @NotNull
    @Positive
    private Integer sortOrder;

    protected Position() {
    }

    public Position(String name, String code, Boolean defender, Integer sortOrder) {
        this.name = name;
        this.code = code;
        this.defender = defender;
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean isDefender() {
        return defender;
    }

    public void setDefender(Boolean defender) {
        this.defender = defender;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
