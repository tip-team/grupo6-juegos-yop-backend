package ar.edu.unq.tip.grupo6.app.model.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailValidator implements ConstraintValidator<Email, String>{

	@Override
	public boolean isValid(final String value, ConstraintValidatorContext context) {
		if(value == null) return false;
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{0,})$";
		return Pattern.compile(EMAIL_PATTERN).matcher(value).matches();
	}

}
