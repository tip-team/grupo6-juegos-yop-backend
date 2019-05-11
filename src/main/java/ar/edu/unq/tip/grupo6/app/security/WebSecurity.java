package ar.edu.unq.tip.grupo6.app.security;

import static ar.edu.unq.tip.grupo6.app.security.Constants.*;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler.ForbiddenExceptionHandler;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		JWTAuthorizationFilter authorizationFilter = new JWTAuthorizationFilter(authenticationManager());
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(AUTH_URL);
		/*
		 * 1. Se desactiva el uso de cookies
		 * 2. Se activa la configuración CORS con los valores por defecto
		 * 3. Se desactiva el filtro CSRF
		 * 4. Se indica que el get productos no requiere autenticación
		 * 5. Se indica que el resto de URLs esten securizadas
		 */
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			.csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.GET, "/api/productos", "/api/productos/{\\d+}", "/api/obtenerUrlPago/{\\d+}").permitAll()
			.antMatchers(HttpMethod.POST, "/api/notifications").permitAll()
			.anyRequest().authenticated().and()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
			.addFilter(authenticationFilter)
			.addFilter(authorizationFilter);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.addExposedHeader(HEADER_AUTHORIZACION_KEY);
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new ForbiddenExceptionHandler();
    }
	
}
