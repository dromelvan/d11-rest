package org.d11.rest.model.jpa.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.d11.rest.model.jpa.D11RestEntity;

public class D11EntityValidator implements ConstraintValidator<ValidatedD11Entity, D11RestEntity> {

    @Override
    public boolean isValid(D11RestEntity d11Entity, ConstraintValidatorContext context) {
        if (d11Entity == null) {
            return false;
        }
        if (d11Entity.getCreatedAt() != null && d11Entity.getUpdatedAt() != null && d11Entity.getUpdatedAt().isBefore(d11Entity.getCreatedAt())) {
            return false;
        }
        return true;
    }

}
