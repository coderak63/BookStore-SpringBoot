package com.abhishek.book_store.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers(HttpMethod.GET,"/api/books/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/books").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/api/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/photos/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/photos/book-id/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/photos").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/photos").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/ratings/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/ratings").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
