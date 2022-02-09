package com.cbnits.CBNITS_TRADE.MyUserDetails;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		AuthRequest user = null;
		String f = (String)(jdbcTemplate.queryForObject("select first_name from users where first_name = ? ",String.class,username));
		String p = (String)(jdbcTemplate.queryForObject("select password from users where first_name = ? ",String.class,username));
//		System.out.println(f);
//		System.out.println(p);
		if(username.equals(f)) {
			
//			AuthRequest authreq = null;
			return new User(username,p,new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not Found");
		}
		
//		return null;  
	}

//	@Autowired
//	ServiceInterface serv;
//	
//	private String passcheck(String firstname) {
//		// TODO Auto-generated method stub
//	//	@Autowired
//		AuthRequest req = null;
//		req.setUsername(firstname);
////		req.setPassword(pass);
//		String username = req.getUsername();
//		String password = req.getPassword();
//		 System.out.println(password);
//		 String md5pass=serv.getMd5(password);
//
//		 String salt = (String) jdbcTemplate.queryForObject("select salt_password from user_password where user_id=?", String.class, username);
//		 byte bytesalt[]=salt.getBytes();
//			System.out.println("bytesalt: "+bytesalt);
//			byte b1 []=serv.hash(md5pass.toCharArray(),bytesalt);			
//			
//			
//			String md5db=(String)jdbcTemplate.queryForObject("select password from user_password where user_id=?",String.class,username);
//			byte b2[]=serv.hash(md5db.toCharArray(),bytesalt);
//			if(Arrays.equals(b1,b2))
//			{
//				return "true";
//			}
//			return "false";	
//		
//	}
	
//	private String lastname(String lp) {
//		AuthRequest req = null;
//		req.setPassword(lp);
//		String last = req.getPassword();
//		return last;
//	}


}
