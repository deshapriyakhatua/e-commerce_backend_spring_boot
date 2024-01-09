package com.spring.rest.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.spring.rest.jwt.JwtAuthenticationEntryPoint;
import com.spring.rest.jwt.JwtAuthenticationFilter;
import com.spring.rest.service.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable())
				.cors(cors -> {

					cors.configurationSource(new CorsConfigurationSource() {

						@Override
						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
							CorsConfiguration cfg = new CorsConfiguration();

							cfg.setAllowedOriginPatterns(Arrays.asList("*"));
							cfg.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
							cfg.setAllowCredentials(true);
							cfg.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type","Content-Type"));
							cfg.setExposedHeaders(Arrays.asList("Authorization"));
							return cfg;
						}
					});

				})
				.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/auth/**").permitAll().requestMatchers("/tester/**").permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();

	}

}
