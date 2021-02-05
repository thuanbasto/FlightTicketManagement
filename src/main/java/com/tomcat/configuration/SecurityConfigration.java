package com.tomcat.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, enable FROM user WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT username, r.name FROM flightticketmanagement.user u " + 
					"RIGHT JOIN role_user ru ON ru.User_Id = u.User_Id " + 
					"RIGHT JOIN flightticketmanagement.role r ON r.Role_Id = ru.Role_Id " + 
					"WHERE username = ?");
//			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable()
			.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll()
			.and().authorizeRequests().antMatchers("/user/**").hasRole("USER").anyRequest().permitAll()
			.and().formLogin().loginPage("/signin").loginProcessingUrl("/signin")
			.usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/admin/dashboard").failureUrl("/signin?error=failed")
			.and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/home")
			.and().exceptionHandling().accessDeniedPage("/signin?error=deny");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}
}
