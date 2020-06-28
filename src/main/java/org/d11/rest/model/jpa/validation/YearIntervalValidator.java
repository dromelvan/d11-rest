package org.d11.rest.model.jpa.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YearIntervalValidator implements ConstraintValidator<YearInterval, String> {

    private static Pattern pattern = Pattern.compile("(\\d{4})-(\\d{4})");

    @Override
    public void initialize(YearInterval yearInterval) {
    }

    @Override
    public boolean isValid(String yearInterval, ConstraintValidatorContext constraintValidatorContext) {
        if (yearInterval != null) {
            Matcher matcher = pattern.matcher(yearInterval);
            if (matcher.matches()) {
                int fromYear = Integer.parseInt(matcher.group(1));
                int toYear = Integer.parseInt(matcher.group(2));
                if (fromYear == toYear - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
