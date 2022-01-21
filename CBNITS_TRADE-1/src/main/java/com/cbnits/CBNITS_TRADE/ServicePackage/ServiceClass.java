package com.cbnits.CBNITS_TRADE.ServicePackage;

import java.math.BigInteger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

//import com.cbnits.CBNITS_TRADE.EntityPackage.EntityClass;

	@Service
	public class ServiceClass implements ServiceInterface {
		public int result() {
			int a=5,b=5;
			return a*b;
		}
		@Autowired
		JdbcTemplate jdbcTemplate;
		 private static final Random RANDOM = new SecureRandom();
		  private static final int ITERATIONS = 10000;
		  private static final int KEY_LENGTH = 256;
		  
		//generating salt
		  @Override
		  public byte[] getNextSalt() {
		    byte[] salt = new byte[16];
		    RANDOM.nextBytes(salt);
		    return salt;
		  }
		//generating salted md5 password
		  @Override
		  public byte[] hash(char[] password, byte[] salt) {
			    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
			    Arrays.fill(password, Character.MIN_VALUE);
			    try {
			      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			      return skf.generateSecret(spec).getEncoded();
			    } 
			    catch (NoSuchAlgorithmException | InvalidKeySpecException e)
			    {
			      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
			    } 
			    finally 
			    {
			      spec.clearPassword();
			    }
			  }
		  
		  
		//generating md5 password
		  @Override
		  public String getMd5(String input)
		    {
		        try {
		  
		            // Static getInstance method is called with hashing MD5
		            MessageDigest md = MessageDigest.getInstance("MD5");
		  
		            // digest() method is called to calculate message digest
		            //  of an input digest() return array of byte
		            byte[] messageDigest = md.digest(input.getBytes());
		  
		            // Convert byte array into signum representation
		            BigInteger no = new BigInteger(1, messageDigest);
		  
		            // Convert message digest into hex value
		            String hashtext = no.toString(16);
		            while (hashtext.length() < 32) {
		                hashtext = "0" + hashtext;
		            }
		            return hashtext;
		        } 
		  
		        // For specifying wrong message digest algorithms
		        catch (NoSuchAlgorithmException e) {
		            throw new RuntimeException(e);
		        }
		    }
		  
		  
			@Override
			public void update1(String user_id,String hashedpass,String salt) {
				String sql="INSERT INTO user_password (user_id,password,salt) VALUES(?,?,?)";
				jdbcTemplate.update(sql,user_id,hashedpass,salt);
			}
				//return jdbcTemplate.query("select * from user_password",new BeanPropertyRowMapper<EntityClass>(EntityClass.class));
			//	return l;
			
		//Authenticate user login	
			@Override
			public Map<String,String> authenticate(String user, String pass) {
				// TODO Auto-generated method stub
				Map <String,String>m=new HashMap<>();
			try {
				//java.nio.charset.Charset charset=StandardCharsets.UTF_16;
				String md5pass=getMd5(pass);
				String salt=(String)jdbcTemplate.queryForObject("select salt from user_password where user_id=?",String.class,user);
				System.out.println(salt);
				byte bytesalt[]=salt.getBytes();
				System.out.println("bytesalt: "+bytesalt);
				byte b1 []=hash(md5pass.toCharArray(),bytesalt);
				System.out.println("b1 :"+b1);
			//	String s=new String (passed);
			//	System.out.println("s= "+s);
				
				
				
				String md5db=(String)jdbcTemplate.queryForObject("select password from user_password where user_id=?",String.class,user);
				System.out.println("md5db: "+md5db);
				byte b2[]=hash(md5db.toCharArray(),bytesalt);
				//byte[] hashedpbyte=hashedp.getBytes();
				System.out.println("b2: "+b2);
			//	String s1=new String (hashedpbyte);
			//	System.out.println("s1= "+s1);
				if(Arrays.equals(b1,b2))
				{
					m.put("Status","ALLOWED");
					
					//jdbcTemplate.update("insert into user_login (id,login,user_id) values (?,?,?)",user,LocalDateTime.now(),null);
				}	
				else 
					m.put("Status","NOT ALLOWED!!! PLEASE ENTER CORRECT PASSWORD");
				
				}
				catch(IncorrectResultSizeDataAccessException e)
				{
					m.put("Status","PLEASE ENTER REGISTERED USER_ID");
				}
				return m;
			}
		/*	@Override
			public Map<String,String> authenticate(String user, String pass) {
				// TODO Auto-generated method stub
				Map <String,String>m=new HashMap<>();
				String salt=jdbcTemplate.queryForObject("select salt from user_password where user_id=?",String.class,user);
				byte[] salt1=salt.getBytes();
			//	String a=new String(salt1);
				
				byte b1[]=hash(pass.toCharArray(),salt1);
				System.out.println("byte b1 :"+b1);
				String s1=new String(b1);
				
				String hashedpass=jdbcTemplate.queryForObject("select password from user_password where user_id=?",String.class,user);
				
				byte b[]=hashedpass.getBytes();
			//	System.out.println("b :"+b);
				//String ss=new String(b);
			//	System.out.println(ss);
				if(Arrays.equals(b1,b))
					System.out.println("equal");
				
				return m;
			}*/
			@Override
			public void update2(UUID user_id,String pass, String hash_pass, String salt, String act_dir, int auth, String email,
					String fname, String lname, String region) {
				// TODO Auto-generated method stub
				jdbcTemplate.update("insert into user_password (user_id,hash_password,salt_password,password,activedirectoryname,authrole,email,firstname,lastname,region) values (?,?,?,?,?,?,?,?,?,?)",user_id,hash_pass,salt,pass,act_dir,auth,email,fname,lname,region);
				
			}
			@Autowired 
			NamedParameterJdbcTemplate temp;
			@Override
			public UUID update3(UUID sales,String fname, String lname, String emailid, String region,String act_dir, int authrole) {
				// TODO Auto-generated method stub
				KeyHolder keyHolder = new GeneratedKeyHolder();
			//	SqlParameterSource data = new BeanPropertySqlParameterSource(users);
			//	jdbcTemplate.update("insert into users (first_name,last_name,region,active_directory,email_id,authorisation_role) values (?,?,?,?,?,?)",fname,lname,region,act_dir,emailid,authrole);
			//	return jdbcTemplate.queryForObject("select id from users where sales_organisation=?",UUID.class,sales);
				String sql="insert into users (first_name,last_name,region,active_directory,email_id,authorisation_role) values (?,?,?,?,?,?)";
				jdbcTemplate.update(connection -> {
			        PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
			        preparedStatement.setString(1, fname);
			        preparedStatement.setString(2, lname);
			        preparedStatement.setString(3, region);
			        preparedStatement.setString(4, act_dir);
			        preparedStatement.setString(5, emailid);
			        preparedStatement.setInt(6, authrole);
			        return preparedStatement;
			    }, keyHolder);
			//    return String.valueOf(keyHolder.getKeyList().get(0).get("id"));
				return (UUID) keyHolder.getKeyList().get(0).get("id");	
			}
			
			
	}
	
				



