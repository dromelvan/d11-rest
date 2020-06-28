package org.d11.rest.model.jpa;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import org.d11.rest.model.D11RestModel;
import org.d11.rest.model.jpa.validation.ValidatedD11Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@ValidatedD11Entity
public class D11RestEntity extends D11RestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    private LocalDateTime updatedAt = createdAt;
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<ConstraintViolation<D11RestEntity>> validate() {
        Set<ConstraintViolation<D11RestEntity>> constraintViolations = D11RestEntity.validator.validate(this);
        return constraintViolations;
    }

    @JsonIgnore
    public boolean isValid() {
        return validate().isEmpty();
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
            this.updatedAt = this.createdAt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass().equals(object.getClass())) {
            D11RestEntity d11Entity = (D11RestEntity) object;
            if (getId() != null && d11Entity.getId() != null && getId().equals(d11Entity.getId())) {
                return true;
            }
        }
        return false;
    }

}
