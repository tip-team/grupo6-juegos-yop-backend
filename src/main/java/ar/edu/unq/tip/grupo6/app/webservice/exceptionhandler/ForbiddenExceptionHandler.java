package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import java.io.IOException;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class ForbiddenExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write(Json.createObjectBuilder().add("message", "Acceso denegado.").build().toString());
    }
    
}