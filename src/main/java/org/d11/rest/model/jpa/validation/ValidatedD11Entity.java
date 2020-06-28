package org.d11.rest.model.jpa.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = D11EntityValidator.class)
public @interface ValidatedD11Entity {

    String message() default "{org.d11.rest.model.jpa.D11Entity.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
