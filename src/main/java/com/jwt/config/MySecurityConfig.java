package com.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JwtAuthenticationFilter jwtFilter;
	
	@Autowired
    private JwtAuthenticationEntryPoint entryPoint;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http
		    .csrf()
		    .disable()
		    .cors()
		    .disable()
		    .authorizeRequests()
		    .antMatchers("/token").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and().exceptionHandling().authenticationEntryPoint(entryPoint);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//	super.configure(auth);
	auth.userDetailsService(customUserDetailsService); 
	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
}
