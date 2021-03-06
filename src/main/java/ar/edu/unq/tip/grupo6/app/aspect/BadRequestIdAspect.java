package ar.edu.unq.tip.grupo6.app.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import ar.edu.unq.tip.grupo6.app.webservice.annotation.BadRequestId;
import ar.edu.unq.tip.grupo6.app.webservice.endpoint.Rest;
import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;

@Aspect
@Component
public class BadRequestIdAspect {
	
	private Paranamer paranamer;
	
	public BadRequestIdAspect() {
		paranamer = new CachingParanamer(new AnnotationParanamer(new BytecodeReadingParanamer()));
	}

	@Around(value = "@annotation(ar.edu.unq.tip.grupo6.app.webservice.annotation.BadRequestId)")
	public Object throwBadRequestOnInvalidId(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = ((MethodSignature) joinPoint.getStaticPart().getSignature()).getMethod();
		Integer idIndex = Arrays.asList(paranamer.lookupParameterNames(method)).indexOf("id");
		if (!idIndex.equals(-1)) {
			String id = (String) joinPoint.getArgs()[idIndex];
			if (StringUtils.isEmpty(id)) {
				throwBadRequest(joinPoint, method, "estar definido.");
			}

			try {
				Integer.valueOf(id);
			} catch (NumberFormatException numberException) {
				throwBadRequest(joinPoint, method, "ser un numero entero.");
			}
		}
	    return joinPoint.proceed();
	}
	
	private void throwBadRequest(ProceedingJoinPoint joinPoint, Method methodIntercepted, String message) throws BadRequestException {
		Rest rest = (Rest) joinPoint.getTarget();
		throw new BadRequestException("Para " + methodIntercepted.getAnnotation(BadRequestId.class).message() + " el id debe " + message, rest.getLogger());
	}
	
}
