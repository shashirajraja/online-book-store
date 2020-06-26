package com.shashirajraja.onlinebookstore.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin().loginPage("/login")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()//.successHandler(successHandler())
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").clearAuthentication(true)
		.deleteCookies("JSESSIONID").invalidateHttpSession(true)
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied")
		.and()
		.authorizeRequests()
		.antMatchers("/actuator/**").hasRole("ADMIN")
		.antMatchers("/api/*").hasAnyRole("ADMIN","CUSTOMER")
		.antMatchers("/customers**").hasRole("CUSTOMER")
		.antMatchers("/").hasAnyRole("ADMIN", "CUSTOMER")
		;
	}

	/*@Bean
	private AuthenticationSuccessHandler successHandler() {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	    successHandler.setTargetUrlParameter("/succeslogin");
	    return successHandler;
	}*/

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
