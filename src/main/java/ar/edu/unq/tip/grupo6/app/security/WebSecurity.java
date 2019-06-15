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
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			.csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.GET, "/favicon.ico").permitAll()
			.antMatchers(HttpMethod.GET, "/api/mp/obtenerUrlPago").permitAll()
			.antMatchers(HttpMethod.GET, "/api/productos", "/api/productos/desc/{\\d+}", "/api/productos/imagen/{\\d+}", "/api/productos/{\\d+}").permitAll()
			.antMatchers(HttpMethod.POST, "/api/mp/notifications", "/api/email").permitAll()
			.anyRequest().authenticated().and()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
			.addFilter(authenticationFilter)
			.addFilter(authorizationFilter);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
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
