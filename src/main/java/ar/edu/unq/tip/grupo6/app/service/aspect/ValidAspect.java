package ar.edu.unq.tip.grupo6.app.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.service.exception.InvalidParameterException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;

@Aspect
@Component
public class ValidAspect {

	@SuppressWarnings("unchecked")
	@Around(value = "@annotation(ar.edu.unq.tip.grupo6.app.service.annotation.Valid)")
	public <T> T valid(ProceedingJoinPoint joinPoint) throws InvalidParameterException, Throwable {
		T objectToValidate = (T) joinPoint.getArgs()[0];
		Set<ConstraintViolation<T>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(objectToValidate);
        if(!violations.isEmpty()) {
            throw new InvalidParameterException(violations.stream().findFirst().get().getMessage());
        }
	    return (T) joinPoint.proceed();
	}
	
}