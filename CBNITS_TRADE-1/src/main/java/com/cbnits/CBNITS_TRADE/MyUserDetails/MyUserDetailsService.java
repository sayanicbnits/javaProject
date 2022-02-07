package com.cbnits.CBNITS_TRADE.MyUserDetails;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthRequest;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		AuthRequest user = null;
		if(username.equals("Ruhaan")) {
		
			return new User("Ruhaan" , "Ruhaan@2001", new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not Found");
		}
		
//		return null;
	}

	private String passcheck() {
		// TODO Auto-generated method stub
	//	@Autowired
		 AuthRequest authRequest=null;
		return authRequest.getPassword();
	}


}
