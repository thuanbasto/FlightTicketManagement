package com.tomcat.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfigration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, enable FROM user WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT username, r.name FROM user u " + 
					"INNER JOIN role_user ru ON ru.User_Id = u.User_Id " + 
					"INNER JOIN role r ON r.Role_Id = ru.Role_Id " + 
					"WHERE username = ?")
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable()
			.authorizeRequests().antMatchers("/admin/dashboard").hasAnyRole("ADMIN","STAFF")
								.antMatchers("/admin/ticket-print").hasAnyRole("ADMIN","STAFF")
								.antMatchers("/admin/ticket-management").hasAnyRole("ADMIN","STAFF")
								.antMatchers("/admin/booking-management").hasAnyRole("ADMIN","STAFF")
								.antMatchers("/admin/customer-management").hasAnyRole("ADMIN","STAFF")
								.antMatchers("/admin/**").hasRole("ADMIN")
								.antMatchers("/staff/**").hasRole("STAFF")
			.and().formLogin().loginPage("/signin").loginProcessingUrl("/signin")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/admin/dashboard").failureUrl("/signin?error=failed")
			.and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/signin")
			.and().exceptionHandling().accessDeniedPage("/signin?error=deny");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}
}
