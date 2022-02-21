package com.cbnits.CBNITS_TRADE.controller;
//import java.nio.charset.StandardCharsets;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthRequest;
import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthResponse;
import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.ResponseMessage;
import com.cbnits.CBNITS_TRADE.ServicePackage.ServiceInterface;
import com.cbnits.CBNITS_TRADE.UserExcel.UserExcelExporter;
import com.cbnits.CBNITS_TRADE.UsersPackage.UserLogin;
//import com.cbnits.CBNITS_TRADE.UsersPackage.UserRepo;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;
import com.cbnits.CBNITS_TRADE.user_passwordpackage.user_password;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.cbnits.CBNITS_TRADE.MyUserDetails.MyUserDetailsService;
import com.cbnits.CBNITS_TRADE.Repository.UserRepository;
import com.cbnits.CBNITS_TRADE.ExcelHelper.ExcelHelper;
import com.cbnits.CBNITS_TRADE.JwtUtils.JwtUtil;


@RestController
//@RequestMapping(value = "/users")

public class ControllerClass {
	@Autowired
	ServiceInterface serv;
	@GetMapping("/hlw")
	public String hello()
	{
		return "hello everyone from CBNITS";
	}
	@PostMapping("/add")
	public int hello1()
	{
		int a=10;
		int b=6;
		a=a+b;	
		return a;
	}
	/*
	@GetMapping("/GeneratePassword")
	public Map<String,String> new1(@ModelAttribute EntityClass data) 
	{
		Map<String,String> m = new HashMap<String,String>();
		//Scanner sc=new Scanner(System.in);
		//System.out.println("enter password");
		//char pass[]=sc.next().toCharArray();
	//	CallingMethod ab=new CallingMethod();
		//return(FirstApplication.md5(String.valueOf(ab.call())))
		String user_id = data.getUser_id();
		String password=data.getPass();
		char passwordarr[] = password.toCharArray();
		byte salt[]=serv.getNextSalt();
		String s=new String(salt);
	//	byte saltedPass[]=serv.hash(data.getPass(),salt);
	//	 String pass = this.serv.md5(String.valueOf(saltedPass));
		byte hashedpass[]=serv.hash(passwordarr, salt);
		String hashedpassstr=new String(hashedpass);
		 serv.update1(user_id,hashedpass,salt);
		 m.put("user_id", user_id);
		 m.put("password",hashedpassstr);
		 return m;
	}
	@GetMapping("/GeneratePassword1")
	public Map<String,String>genpass(@ModelAttribute EntityClass data)
	{
		Map<String,String>m=new HashMap<>();
		byte salt[]=serv.getNextSalt();
		System.out.println(salt);
		String pass=data.getPass();
		String user_id=data.getUser_id();
		char[] passchar=pass.toCharArray();
		byte []passbyte=pass.getBytes();
		byte[] hashedpass=serv.hash(passchar, salt);
		System.out.println(hashedpass);
		serv.update1(user_id,hashedpass, String.valueOf(salt));
		m.put("user_id", user_id);
		m.put("password",pass);
		return m;
		
		
	}*/
	/*@GetMapping("/genpass")
	public Map<String,String>genpass1(@ModelAttribute EntityClass data)
	{	
		try
		{
		Map<String,String>m=new HashMap<>();
		byte[]salt=serv.getNextSalt();
	//	String s=new String(salt,StandardCharsets.UTF_8);
		String pass=data.getPass();
			
		String user_id=data.getUser_id();
		String md5pass=serv.getMd5(pass);
	//	char md5char[]=md5pass.toCharArray();
	//	byte[] hashedbyte=serv.hash(md5char, salt);
		serv.update1(user_id,md5pass,String.valueOf(salt));
		m.put("user_id", user_id);
		m.put("password",md5pass);
		return m;
		}
		catch(Exception e)
		{
			Map<String,String>n=new HashMap<>();
			n.put("Status: ","User_id already in use");
			return n;
		}
	}
	
	@PostMapping("/login")
	public Map<String,String> authenticate(@ModelAttribute EntityClass data)
	{
		String user=data.getUser_id();
		String pass=data.getPass();
		return(serv.authenticate(user,pass));
		
	}*/
	@PostMapping("/user_password")
	public Map<String,String>input(@ModelAttribute user_password data, Users u)
	{  
		Map<String,String>m=new HashMap<>();
	
		UUID id=data.getId();
		UUID user_id=data.getUser_id();

		String pass=data.getPassword();
		String hash_pass=serv.getMd5(pass);
		byte[] salt=serv.getNextSalt();
//		String act_dir=data.getActivedirectoryname();
//		int auth=data.getAuthrole();
//		String email=data.getEmail();
//		String fname=data.getFirstname();
//		String lname=data.getLastname();
//		String region=data.getRegion();
		
//		serv.update2(user_id,pass,hash_pass,String.valueOf(salt));
		
		m.put("password",pass);
		m.put("hash_password",hash_pass);
		m.put("salt_pass",String.valueOf(salt));
//		
//	//	m.put("authrole",auth);
////		m.put("email",email);
////		m.put("firstname",fname);
////		m.put("lastname",lname);
////		m.put("region",region);
//		System.out.println("id="+id);
		return m;
//		
	}
	


	
	@PostMapping("/details")
	public ResponseEntity<Object> createuser(@Valid @ModelAttribute Users data, BindingResult result) throws MethodArgumentNotValidException
	{
//		
		 if (result.hasErrors()) {
        throw new MethodArgumentNotValidException(null, result);
//        return true;
         } 

		 else {
//		UUID sales = datapass.getId();
//		data.setSales_org(sales);
		String fname=data.getFirst_name();
		String lname=data.getLast_name();
		String email_id=data.getEmail_id();
		String region=data.getRegion();
		UUID salesorg=data.getSales_org();
		int authrole=data.getAuthorisation_role();
		String pass = data.getPassword();
		String hash_pass=serv.getMd5(pass);
		byte[] salt=serv.getNextSalt();
		String s = String.valueOf(salt);
		String active_directory = data.getActive_directory();
		 serv.insert(salesorg,fname,lname,email_id,region,active_directory ,authrole,hash_pass,s );
//		data.setId(id);
//		Map<String,String>m=new HashMap<>();
//		m.put("active_directory", act_dir);
//		return id;
		 
		 Map<String,Object> body = new HashMap<>();
		 body.put("error", false);
		
		 return new ResponseEntity<>(body ,HttpStatus.OK);
		 }
		
//		 return ResponseEntity.ok("User data is valid");
		 
		
	}
	
	@PostMapping("/auth")
	public Map <String,Object> get(@ModelAttribute UserLogin login) {
		UUID user_id = login .getUser_id();
		UUID sales_orgs = login.getSales_orgs();
		String password = login.getPassword();
		Map<String, Object> map = new HashMap<String, Object>();
		map = serv.fetch(user_id,password , sales_orgs);
		
		return map;
	}
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	 @Autowired
	    private MyUserDetailsService userDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token" , method = RequestMethod.POST)
	public ResponseEntity<?> getToken(@ModelAttribute AuthRequest req , BindingResult r) throws RuntimeException  {
	
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
//			);
//		}
		if(r.hasErrors()) {
			 throw new RuntimeException("Invalid user");
		}
		else {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
			);
		}
		
		

  		
		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(req.getUsername()) ;
		final String jwt = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT: " + jwt);

		return ResponseEntity.ok(new AuthResponse(jwt));
	
	}
	
	@GetMapping("/fetchuser")
	public ResponseEntity<?> fetchuser() {
		List<Users> l = new ArrayList();
		l = serv.userList();
		return ResponseEntity.ok(l);
	}
	
	@GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
//		
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
//         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users_List" + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Users> listUsers = serv.userList();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }
	@Autowired
    		JdbcTemplate jdbcTemplate;
	@PostMapping("/insertExcel")
	public void insertInExcel(@ModelAttribute Users data) throws InvalidFormatException {
//		XSSFWorkbook workbook1 = new XSSFWorkbook();
//		  
//        // spreadsheet object
//        XSSFSheet spreadsheet
//            = workbook1.createSheet(" User Data ");
	
		String excelFilePath = "C:\\Users\\cbnits\\Downloads\\users_2022-02-20_19_16_20.xlsx";
        
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            Sheet sheet = workbook.getSheetAt(0); 
//        	 XSSFWorkbook workbook = new XSSFWorkbook();
//        	  
//             // spreadsheet object
//             XSSFSheet sheet
//                 = workbook.createSheet(" User Data ");
//             Row row = sheet.createRow(0);
//             
//             CellStyle style = workbook.createCellStyle();
//             XSSFFont font = workbook.createFont();
//             font.setBold(true);
//             font.setFontHeight(16);
//             style.setFont(font);
//              
//             serv.createCell(row, 0, "id", style);      
//             serv.createCell(row, 1, "first_name", style);       
//             serv.createCell(row, 2, "last_name", style);    
//             serv.createCell(row, 3, "region", style);
//             serv.createCell(row, 4, "active_directory", style);
//             serv.createCell(row, 5, "email_id", style);
//             serv.createCell(row, 6, "authorisation_role", style);
//             serv.createCell(row, 7, "sales_organisation", style);
//             serv.createCell(row, 8, "password", style);
//             serv.createCell(row, 9, "salt_pass", style);
//              
         
           
    		String salesorg=String.valueOf(data.getSales_org());

    		String pass = data.getPassword();
    		String hash_pass=serv.getMd5(pass);
    		byte[] salt=serv.getNextSalt();
    		String s = String.valueOf(salt);
    		int role = data.getAuthorisation_role();
    	
    		     
    				UUID uuid = UUID.randomUUID();
    		    	int rowCount = sheet.getLastRowNum()+1;
//    		    	 XSSFWorkbook workbook;
    		        CellStyle style = workbook.createCellStyle();
    		        XSSFFont font = (XSSFFont) workbook.createFont();
    		        font.setFontHeight(14);
    		        style.setFont(font);
    		                 
    		       
    		            Row row1 = sheet.createRow(rowCount);
    		            int columnCount = 0;
    		             
    		        
    		            
    		            serv.createCell(row1, columnCount++,(String.valueOf(uuid)), style);
    		            serv.createCell(row1, columnCount++, data.getFirst_name(), style);
    		            serv.createCell(row1, columnCount++, data.getLast_name(), style);
    		            serv.createCell(row1, columnCount++, data.getRegion(), style);
    		            serv.createCell(row1, columnCount++, data.getActive_directory(), style);
    		            serv.createCell(row1, columnCount++, data.getEmail_id(), style);
    		            serv.createCell(row1, columnCount++, String.valueOf(role), style);
    		            serv.createCell(row1, columnCount++,salesorg, style);
    		            serv.createCell(row1, columnCount++, hash_pass, style);
    		            serv.createCell(row1, columnCount++, s, style);
    		        
    		   
//    		inputStream.close();
 
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\cbnits\\Downloads\\users_2022-02-20_19_16_20.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            
	
	}
        catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }
	
	 @Autowired
	 UserRepository repository;
	 
	 @Autowired
	 ExcelHelper excelhelper;
	 
//	List<UserRepo>
	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		  
		  	    String message = "";
	    if (excelhelper.hasExcelFormat(file)) {
	      try {
	        serv.save(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        System.out.println(e);
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }
	    message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		  
	  }
}
		  
		  
		  
		  
		  
		  
		  
//		  List<UserRepo> students = new ArrayList<>();
//	        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
//	        // Read student data form excel file sheet1.
//	        XSSFSheet worksheet = workbook.getSheetAt(0);
//	        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
//	            if (index > 0) {
//	                XSSFRow row = worksheet.getRow(index);
//	                UserRepo student = new UserRepo();
//	                student.id = UUID.fromString(getCellValue(row, 0));
//	                student.first_name = getCellValue(row, 1);
//	                student.last_name = getCellValue(row, 2);
//	                student.region = getCellValue(row, 3);
//	                student.active_directory = getCellValue(row, 4);
//	                student.email_id = getCellValue(row, 5);
//	                student.authorisation_role = convertStringToInt(getCellValue(row, 6));
//	                student.sales_org = UUID.fromString(getCellValue(row, 7));
//	                student.password = getCellValue(row, 8);
//	                students.add(student);
//	            }
//	        }
//	        // Save to db.
//	        List<Users> entities = new ArrayList<>();
//	        if (students.size() > 0) {
//	            students.forEach(x->{
//	                Users entity = new Users();
//	                entity.id = x.id;
//	                entity.first_name = x.first_name;
//	                entity.last_name = x.last_name;
//	                entity.region = x.region;
//	                entity.active_directory = x.active_directory;
//	                entity.email_id = x.email_id;
//	                entity.authorisation_role = x.authorisation_role;
//	                entity.sales_org = x.sales_org;
//	                entity.password = x.password;
//	                entities.add(entity);
//	            });
//	            repository.saveAll(entities);
//	        }
//	        return students;
//	    }
//	    private int convertStringToInt(String str) {
//	        int result = 0;
//	        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
//	            return result;
//	        }
//	        result = Integer.parseInt(str);
//	        return result;
//	    }
//	    private String getCellValue(Row row, int cellNo) {
//	        DataFormatter formatter = new DataFormatter();
//	        Cell cell = row.getCell(cellNo);
//	        return formatter.formatCellValue(cell);
//	    }
		  
		  
		  
		  
		  
		  
		 
	         
    		 


			
     
	            

 


	
