package ar.edu.unq.tip.grupo6.app.aspect;

import javax.ws.rs.core.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CorsAspect {
	
	@AfterReturning(pointcut = "execution(* ar.edu.unq.tip.grupo6.app.webservice.endpoint..*(..))", returning= "result")
	public void alrededor(JoinPoint joinPoint, Object result) {
		Response response = (Response) result;
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers",
                "x-requested-with, origin, content-type, accept, authorization");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}

}
