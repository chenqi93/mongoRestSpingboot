package com.threezebra.restapi.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.threezebra.common.DateUtils;

import java.time.DateTimeException;


/**
 * @author vikas.sharma
 *
 */
public class IntDateValidator implements ConstraintValidator<IntDateValidate, Integer> {

    private IntDateValidate intDateValidate;

    @Override
    public void initialize(IntDateValidate intDateValidate) {
        this.intDateValidate = intDateValidate;
    }

    @Override
    public boolean isValid(Integer intDate, ConstraintValidatorContext constraintValidatorContext) {
        if (intDate == null) {
            return true;
        }
        try {
            DateUtils.localDate(intDate);
        } catch (DateTimeException ex) {
            return false;
        }

        return true;
    }

}
