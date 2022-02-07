package com.cbnits.CBNITS_TRADE.config;

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

import com.cbnits.CBNITS_TRADE.Filters.JwtFilter;
import com.cbnits.CBNITS_TRADE.MyUserDetails.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
		@Autowired
		private MyUserDetailsService myUserDetailsService;
		@Autowired
		private JwtFilter jwtFilter;

//		@Autowired
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(myUserDetailsService);
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}


		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/token").permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		}
}

