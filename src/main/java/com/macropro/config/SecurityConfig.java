package com.macropro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity//(debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    //@Qualifier("currentUserDetailsService")
    private UserDetailsService currentUserDetailsService;
	
	//@Autowired
	//private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(currentUserDetailsService)
			.passwordEncoder(passwordEncoder());
		/*.jdbcAuthentication()
			.dataSource(securityDataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("SELECT username, password, enabled from user where username = ?")
			.authoritiesByUsernameQuery(
                "SELECT u.username, a.authority " +
                "FROM user_authorities a, user u " +
                "WHERE u.username = ? " +
                "AND u.id = a.user_id");*/
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/resources/**").permitAll()
				.antMatchers("/account/signup").permitAll()
				.antMatchers("/account/addUser").permitAll()
				//.antMatchers("/account/goals").hasAuthority("USER_GNS")
				.antMatchers("/account/**").hasAnyAuthority("USER_GNS", "USER_GS")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/account/login")
				.loginProcessingUrl("/authenticateTheUser")
				.defaultSuccessUrl("/account/")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
}
