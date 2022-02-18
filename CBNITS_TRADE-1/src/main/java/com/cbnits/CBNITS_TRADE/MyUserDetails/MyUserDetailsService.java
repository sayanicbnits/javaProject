package com.cbnits.CBNITS_TRADE.MyUserDetails;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.cbnits.CBNITS_TRADE.Repo.UserRepo;
import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthRequest;
import com.cbnits.CBNITS_TRADE.ServicePackage.ServiceInterface;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;


@Service
public class MyUserDetailsService implements UserDetailsService{

//	@Autowired
//	private UserRepo userdao;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public UserDetails loadUserByUsername(String username) throws RuntimeException {

		
//		if (username == null) {
//            throw new BadCredentialsException(
//                    "No user found with username: " + username);
//        }
		String f = (String)(jdbcTemplate.queryForObject("select first_name from users where first_name = ? ",String.class,username));
		String p = (String)(jdbcTemplate.queryForObject("select password from users where first_name = ? ",String.class,username));
//		System.out.println(f);
//		System.out.println(p);
		if(username.equals(f)) {
			
			return new User(username,p,new ArrayList<>());
		}
		
		else {
			throw new RuntimeException("User not Found");
		}
		
//		return null;  
	}


}
