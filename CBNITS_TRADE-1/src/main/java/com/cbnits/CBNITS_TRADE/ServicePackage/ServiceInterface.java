package com.cbnits.CBNITS_TRADE.ServicePackage;

import java.util.List;

import java.util.Map;
import java.util.UUID;

//import com.cbnits.CBNITS_TRADE.EntityPackage.EntityClass;

public interface ServiceInterface {
	public int result();
	public byte[] getNextSalt();	
	public byte[] hash(char[] password, byte[] salt);
	public String getMd5(String input);
	//public void update1(String user_id, String hashedpassst, String s);
	public Map<String, String> authenticate(String user, String pass);
	//public void update1(String user_id, byte[] hashedpass, byte[] salt);
	public void update1(String user_id, String md5pass, String salt);
//	public void update2(String pass, String hash_pass, String salt, String act_dir, int auth, String email,
//			String fname, String lname, String region);
	public void update2(UUID user_id, String pass, String hash_pass, String valueOf, String act_dir, int auth,
			String email, String fname, String lname, String region);
	public UUID update3(UUID sales,String fname, String lname, String emailid, String region, String act_dir, int authrole);



}
