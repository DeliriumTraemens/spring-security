package org.nick.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/authenticated/**").authenticated()/*Доступ в аутентифицированные адреса*/
				.antMatchers("/only_for_admins/**").hasRole("ADMIN")/*Доступ по роли*/
				.antMatchers("/read_profile/**").hasAuthority("READ_PROFILE")/*Доступ по авторити адреса*/
					.and()
//				.httpBasic()
				.formLogin()
//				.loginPage("/login")
				.permitAll()
					.and()
				.logout()
				.permitAll();
	}
//	---------------------- JDBC AUTH ----------------------
	@Bean
	public JdbcUserDetailsManager users(DataSource dataSource){
		
//		UserDetails user = User.builder()
//				.username("user")
//				.password("{bcrypt}$2a$12$6GDz.wzfTTXZwL26H8BGYOnG2HxxrBD7es2Qh9J9hJMAwxeHORboS")
//				.roles("USER")
//				.build();
//
//		UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$G.kaE47bsKMFrTQTQfWNW.MkYnvv.sDQAT0ABOTvXaw23s3fi5K.m")
//                .roles("USER","ADMIN")
//                .build();
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
//		if (jdbcUserDetailsManager.userExists(user.getUsername())) {
//			jdbcUserDetailsManager.deleteUser(user.getUsername());
//		}
//
//		if(jdbcUserDetailsManager.userExists(admin.getUsername())){
//			jdbcUserDetailsManager.deleteUser(admin.getUsername());
//		}
//
//		jdbcUserDetailsManager.createUser(user);
//		jdbcUserDetailsManager.createUser(admin);
		
		return jdbcUserDetailsManager;
	}
	
//	-------------------- IN MEMORY AUTH -------------------
//	@Bean
//	public UserDetailsService users(){
//		UserDetails user = User.builder()
//				.username("user")
//				.password("{bcrypt}$2a$12$6GDz.wzfTTXZwL26H8BGYOnG2HxxrBD7es2Qh9J9hJMAwxeHORboS")
//				.roles("USER")
//				.build();
//
//		UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$G.kaE47bsKMFrTQTQfWNW.MkYnvv.sDQAT0ABOTvXaw23s3fi5K.m")
//                .roles("USER","ADMIN")
//                .build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
}

//.antMatchers("/admin/**").hasAnyRole("ADMIN","SUPERADMIN")/*Допуск в админку по ролям*/
//.antMatchers("/profile/**").authenticated()/*См выше - может быть и hasAuthority*/