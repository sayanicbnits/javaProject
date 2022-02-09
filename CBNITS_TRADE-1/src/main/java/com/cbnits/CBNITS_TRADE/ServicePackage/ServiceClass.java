package com.cbnits.CBNITS_TRADE.ServicePackage;

import java.math.BigInteger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthRequest;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.sql.DataSource;

import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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
//			@Override
//			public void update2(UUID user_id,String pass, String hash_pass, String salt) {
//				// TODO Auto-generated method stub
//				jdbcTemplate.update("insert into user_password (user_id,hash_password,salt_password,password,activedirectoryname,authrole,email,firstname,lastname,region) values (?,?,?,?,?,?,?,?,?,?)",user_id,hash_pass,salt,pass,act_dir,auth,email,fname,lname,region);
//				
//			}
			
			
			
			
			
//			@Autowired 
//			NamedParameterJdbcTemplate temp;
//			@Override
//			public UUID insert1(String country, String currency, int plants, String bergu, String sales_organisation) {
//				// TODO Auto-generated method stub
//				KeyHolder keyHolder = new GeneratedKeyHolder();
//			//	SqlParameterSource data = new BeanPropertySqlParameterSource(users);
//			//	jdbcTemplate.update("insert into users (first_name,last_name,region,active_directory,email_id,authorisation_role) values (?,?,?,?,?,?)",fname,lname,region,act_dir,emailid,authrole);
//			//	return jdbcTemplate.queryForObject("select id from users where sales_organisation=?",UUID.class,sales);
//				String sql="with rows as (insert into sales_organisation (country,currency,plants,bergu,sales_organisation) values (?,?,?,?,?) RETURING id)";
//			
//				jdbcTemplate.update(connection -> {
//			        PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
//			        preparedStatement.setString(1, country);
//			        preparedStatement.setString(2, currency);
//			        preparedStatement.setInt(3, plants);
//			        preparedStatement.setString(4, bergu);
//			        preparedStatement.setString(5, sales_organisation);
//			        
//			        return preparedStatement;
//			    }, keyHolder);
//			//    return String.valueOf(keyHolder.getKeyList().get(0).get("id"));
//				return (UUID) keyHolder.getKeyList().get(0).get("id");	
//			}


			
			
			
			
//			@Autowired 
//			NamedParameterJdbcTemplate temp;
			@Override
			public void insert(UUID salesorg,String fname, String lname, String email_id, String region, String active_directory, int authrole,String hash_pass ,String s) {
				// TODO Auto-generated method stub
//				KeyHolder keyHolder = new GeneratedKeyHolder();
			//	SqlParameterSource data = new BeanPropertySqlParameterSource(users);
			//	jdbcTemplate.update("insert into users (first_name,last_name,region,active_directory,email_id,authorisation_role) values (?,?,?,?,?,?)",fname,lname,region,act_dir,emailid,authrole);
			//	return jdbcTemplate.queryForObject("select id from users where sales_organisation=?",UUID.class,sales);
				String sql=" INSERT into users (first_name,last_name,region,active_directory,email_id,authorisation_role,sales_organisation,password) values (?,?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,fname,lname,region,active_directory,email_id,authrole,salesorg,hash_pass);
				
				String l = "update users SET sales_organisation = sales_organisation.id from sales_organisation WHERE sales_organisation.country = users.region";
				jdbcTemplate.update(l);
				
				
				String p = "insert into user_password(hash_password,salt_password) values(?,?)";
				jdbcTemplate.update(p,hash_pass,s);
				jdbcTemplate.update("update user_password SET user_id = users.id from users WHERE users.password = user_password.hash_password");
				
				
//				String query = "Insert into users (sales_organisation) WHERE region = 'IN' SELECT id from sales_organisation WHERE country = 'IN' ";
//				jdbcTemplate.update(query);
//				WITH getval(id) as
//			    (INSERT INTO table_a (some_col) VALUES (some_val) RETURNING id) 
//			INSERT into table_b (id) SELECT id from getval;
				
//				jdbcTemplate.update(connection -> {
//			        PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
//			        preparedStatement.setString(1, fname);
//			        preparedStatement.setString(2, lname);
//			        preparedStatement.setString(3, region);
//			        preparedStatement.setString(4, active_directory);
//			        preparedStatement.setString(5, email_id);
//			        preparedStatement.setInt(6, authrole);
//			        preparedStatement.setObject(7,salesorg);
//			        preparedStatement.setString(8, hash_pass);
//			        
//			        return preparedStatement;
//			    }, keyHolder);
//			//    return String.valueOf(keyHolder.getKeyList().get(0).get("id"));
//				return (UUID) keyHolder.getKeyList().get(0).get("id");	
			}
			
		
			@Override
			public  Map <String,Object> fetch(UUID user_id,String password, UUID sales_orgs) {
//				String sql="select * from users where id = ? and sales_organisation = ?";
//				 jdbcTemplate.update(sql,user_id,sales_orgs);
				
				 Map <String,Object> m =new HashMap<>();
				 
				 String md5pass=getMd5(password);
                
				    String sql = "select salt_password from user_password where user_id=?";

				    String salt = (String) jdbcTemplate.queryForObject(sql, String.class, user_id );

//				jdbcTemplate.update("select salt_password from user_password where user_id=?",user_id);
//					System.out.println(salt);
					byte bytesalt[]=salt.getBytes();
//					System.out.println("bytesalt: "+bytesalt);
					byte b1 []=hash(md5pass.toCharArray(),bytesalt);
//					System.out.println("b1 :"+b1);
				//	String s=new String (passed);
				//	System.out.println("s= "+s);
					
				
					String md5db=(String)jdbcTemplate.queryForObject("select hash_password from user_password where user_id=?",String.class,user_id);
//					System.out.println("md5db: "+md5db);
					byte b2[]=hash(md5db.toCharArray(),bytesalt);
					
//					System.out.println("b2: "+b2);
				//	String s1=new String (hashedpbyte);
				//	System.out.println("s1= "+s1);
					if(Arrays.equals(b1,b2))
					{
//					    and sales_organisation = ? 
						String f = (String)(jdbcTemplate.queryForObject("select first_name from users where id = ? ",String.class,user_id));
						String l = (String)(jdbcTemplate.queryForObject("select last_name from users where id = ? ",String.class,user_id));
						String a = (String)(jdbcTemplate.queryForObject("select authorisation_role from users where id = ? ",String.class,user_id));
//						String i = (String)(jdbcTemplate.queryForObject("select sales_organisation from users where id = ? ",String.class,user_id));
						String r = (String)(jdbcTemplate.queryForObject("select region from users where id = ? ",String.class,user_id));
//						String d = (String)(jdbcTemplate.queryForObject("select active_directory from users where id = ? ",String.class,user_id));
						
						m.put("Status","Success");
						m.put("first_name", f);
						m.put("last_name", l);
						m.put("auth_role", a);
//						m.put("sales_id", i);
						m.put("salesorg_name", r);
//						m.put("active_dir", d);
						
						return m;
						//jdbcTemplate.update("insert into user_login (id,login,user_id) values (?,?,?)",user,LocalDateTime.now(),null);
					}	
					else 
						m.put("Status","NOT ALLOWED!!! PLEASE ENTER CORRECT PASSWORD");
						return m;
					}
			
//					catch(IncorrectResultSizeDataAccessException e)
//					{
//						m.put("Status","PLEASE ENTER REGISTERED USER_ID");
//					}
			
//			@Override
//			public String check(String username, String password) {
//				// TODO Auto-generated method stub
////				String f = (String)(jdbcTemplate.queryForObject("select first_name from users where password = ? ",String.class,password));
//				if(username.equals("Ruhaan"))
//				{
//					return username;
//				}
//				else 
//					return null;
//			}
			

			@Override
			public List<Users> userList() {
				List<Users> list = jdbcTemplate.query("SELECT * FROM users", new RowMapper<Users>() {

					@Override
					public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
						Users u = new Users();

						u.setId(UUID.fromString(String.valueOf(rs.getObject("id"))));
						u.setFirst_name(rs.getString("first_name"));
						u.setLast_name(rs.getString("last_name"));
						u.setRegion(rs.getString("region"));
						u.setActive_directory(rs.getString("active_directory"));
						u.setEmail_id(rs.getString("email_id"));
						u.setAuthorisation_role(rs.getInt("authorisation_role"));
						u.setSales_org(UUID.fromString(String.valueOf(rs.getObject("sales_organisation"))));
						u.setPassword(rs.getString("password"));
						
						return u;
					}

				});

				return list;
			}

			
			
			
			}
			
			

	
				



