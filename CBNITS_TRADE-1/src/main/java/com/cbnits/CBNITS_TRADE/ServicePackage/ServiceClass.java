package com.cbnits.CBNITS_TRADE.ServicePackage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cbnits.CBNITS_TRADE.EntityPackage.EntityClass;

	@Service
	public class ServiceClass implements ServiceInterface {
		public int result() {
			int a=5,b=5;
			return a*b;
		}
		 private static final Random RANDOM = new SecureRandom();
		  private static final int ITERATIONS = 10000;
		  private static final int KEY_LENGTH = 256;
		  
//		generating salt
		  
		  public byte[] getNextSalt() {
		    byte[] salt = new byte[16];
		    RANDOM.nextBytes(salt);
		    return salt;
		  }
		//generating salted password
		  public byte[] hash(char[] password, byte[] salt) {
			    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
			    Arrays.fill(password, Character.MIN_VALUE);
			    try {
			      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			      return skf.generateSecret(spec).getEncoded();
			    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
			    } finally {
			      spec.clearPassword();
			    }
			  }
		  
		  
			//generating md5 password

		  public String md5(String input) {
				
				String md5 = null;
				
				if(null == input) return null;
				
				try {
					
				//Create MessageDigest object for MD5
				MessageDigest digest = MessageDigest.getInstance("MD5");
				
				//Update input string in message digest
				digest.update(input.getBytes(), 0, input.length());

				//Converts message digest value in base 16 (hex) 
				md5 = new BigInteger(1, digest.digest()).toString(16);

				} catch (NoSuchAlgorithmException e) {

					e.printStackTrace();
				}
				return md5;
			}
		  @Autowired
			JdbcTemplate jdbcTemplate;
			@Override
			public List<EntityClass> update1(String user_id,String pass) {
				String sql="INSERT INTO user_password (user_id,password) VALUES(?,?)";
				jdbcTemplate.update(sql,user_id,pass);
				return jdbcTemplate.query("select * from user_password",new BeanPropertyRowMapper<EntityClass>(EntityClass.class));
			//	return l;
			
			}

}

