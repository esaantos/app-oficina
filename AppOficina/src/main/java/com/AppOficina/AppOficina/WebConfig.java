package com.AppOficina.AppOficina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

	// Método que configura usuários e escopo de atuação no sistema
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder)throws Exception{
		
		builder
		.inMemoryAuthentication()
		.withUser("everson").password("{noop}everson").roles("USER") //noop anula critptografia , regra de usuário
		.and().withUser("root").password("{noop}root").roles("ADMIN"); // regra de admin
		
	}
	
	// Método que configura quais seções do site podem ser acessadas com e sem login
	protected void configure(HttpSecurity http) throws Exception{
		
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll();			
		http
		.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/home**").permitAll().anyRequest()
		.authenticated().and().formLogin().permitAll()
		.and().logout().permitAll()
		.and().csrf().disable();
	}

	
}
