package br.com.dell.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import br.com.dell.backend.config.autentication.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserPrincipalDetailsService userPrincipalDetailsService;

	public SecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService) {
		super();
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable().authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/configuration/ui",
				"/configuration/security", "/swagger-ui.html");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}qazxsw123").
		 * roles("USER").and().withUser("jose") .password("{noop}12345").roles("USER")
		 */
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		var daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
