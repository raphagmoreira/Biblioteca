package br.biblioteca.livros;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
       return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   http.csrf().disable()
 		 .authorizeRequests()
 		 .antMatchers(HttpMethod.GET, "/user/registration").permitAll()
 		 .antMatchers(HttpMethod.POST, "/user/registration").permitAll()
 		 .antMatchers(HttpMethod.GET, "/user/list").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/user/listadmin").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/emprestimos/novo").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/emprestimos/alterar/{id}").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/emprestimos/excluir/{id}").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/livros/novo").hasRole("BASIC")
		 .antMatchers(HttpMethod.GET, "/livros/alterar/{id}").hasRole("BASIC")
		 .antMatchers(HttpMethod.GET, "/livros/excluir/{id}").hasRole("BASIC")
		 .antMatchers(HttpMethod.GET, "/autores/novo").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/autores/alterar/{id}").hasRole("BASIC")
 		 .antMatchers(HttpMethod.GET, "/autores/excluir/{id}").hasRole("BASIC")
 		 .and()
 		 .formLogin()
 			 .loginPage("/user/login")
 			 .permitAll()
 		 .and().logout().permitAll();
   }
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
   }

}