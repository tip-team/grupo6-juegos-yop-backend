package ar.edu.unq.tip.grupo6.app.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;

public class Validator {

	public static <T> void validate(T object) throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(object);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
	
}
