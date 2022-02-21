package com.cbnits.CBNITS_TRADE.ExcelHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cbnits.CBNITS_TRADE.Repository.UserRepository;
import com.cbnits.CBNITS_TRADE.ServicePackage.ServiceInterface;
//import com.cbnits.CBNITS_TRADE.UsersPackage.UserRepo;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;
//import com.sun.jmx.mbeanserver.Repository;


//@Transactional
@Service

public class ExcelHelper {
	
	
	@Autowired
	static
	ServiceInterface serv;
		@Autowired
//		static
		JdbcTemplate jdbcTemplate;
		
//	application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//	  static String[] HEADERs = { "id", "first_name", "last_name", "region","active_directory","email_id","authorisation_role","sales_organisation","password" };
//	  static String SHEET = "Users";
	  
	  @Autowired
		 UserRepository repository;
	  
	  public  boolean hasExcelFormat(MultipartFile file) {
		  System.out.println(file.getContentType());
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	  public  List<Users> excelToUsers(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);
	      XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
	      Iterator<Row> rows = sheet.iterator();
	      List<Users> tutorials = new ArrayList<Users>();
	      int rowNumber = 0;
	      while (rows.hasNext()) {
	    	
	        Row currentRow = rows.next();
//	        System.out.println(currentRow);
	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }
	          
	        
	     

	        
	        Iterator<Cell> cellsInRow = currentRow.iterator();
	        Users tutorial = new Users();
//	        UUID id = tutorial.getId();
	       
	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();
//	          System.out.println(currentCell.getStringCellValue());
	           
	          switch (cellIdx) {
	          case 0:
	        	
	        	tutorial.setId(UUID.fromString((currentCell.getStringCellValue())));
	        	break;
	          case 1:
	        	  tutorial.setFirst_name(currentCell.getStringCellValue());
	            
	            break;
	          case 2:
	            tutorial.setLast_name(currentCell.getStringCellValue());
	            break;
	          case 3:
	            tutorial.setRegion(currentCell.getStringCellValue());
	            break;
	        
	          case 4:
		            tutorial.setActive_directory(currentCell.getStringCellValue());
		            break;
	          case 5:
		            tutorial.setEmail_id(currentCell.getStringCellValue());
		            break;
	          case 6:
//	        	  System.out.println( tutorial.getAuthorisation_role());
		            tutorial.setAuthorisation_role (Integer.parseInt((currentCell.getStringCellValue())));
		            
		            break;
	          case 7:
		            tutorial.setSales_org(UUID.fromString((currentCell.getStringCellValue())));
		            break;
	          case 8:
		            tutorial.setPassword(currentCell.getStringCellValue());
		            break;
	          case 9:
		            tutorial.setSalt((currentCell.getStringCellValue()));
		            break;

	          default:
	            break;
	          }
	          cellIdx++;
	        }
	        
	        UUID id = tutorial.getId();
	        String fname=tutorial.getFirst_name();
			String lname=tutorial.getLast_name();
			String email_id=tutorial.getEmail_id();
			String region=tutorial.getRegion();
			int authrole=tutorial.getAuthorisation_role();
			String active_directory = tutorial.getActive_directory();
			String pass = tutorial.getPassword();
			UUID sales_org = tutorial.getSales_org();
			String s = tutorial.getSalt();
			
       	  if(repository.existsById(id)){
       		  continue;
       	  }
       	
       	  else {
       		System.out.println(lname);
			tutorials.add(tutorial);
			insert1(id,sales_org, fname,lname, email_id,region,active_directory, authrole,pass,s);
			System.out.println(lname);
       	  }
//       	  
	      
	      }
	      
	      
	      
	      workbook.close();
	      return tutorials;
	    }
	    catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }
	  
	  public  void insert1(UUID id,UUID sales_org, String fname, String lname, String email_id, String region,String active_directory, int authrole, String pass, String s) {
			 System.out.println(fname);
			 String sql=" INSERT into users (id,first_name,last_name,region,active_directory,email_id,authorisation_role,sales_organisation,password) values (?,?,?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,id,fname,lname,region,active_directory,email_id,authrole,sales_org,pass);
				String p = "insert into user_password(hash_password,salt_password) values(?,?)";
				jdbcTemplate.update(p,pass,s);
				jdbcTemplate.update("update user_password SET user_id = users.id from users WHERE users.password = user_password.hash_password");
					
				
				
		 }
	}