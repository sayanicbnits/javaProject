package com.cbnits.CBNITS_TRADE.UserExcel;

import java.util.List;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;

import java.io.IOException;
import java.util.UUID;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Users> listUsers;
     
    public UserExcelExporter(List<Users> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);      
        createCell(row, 1, "First_name", style);       
        createCell(row, 2, "Last_name", style);    
        createCell(row, 3, "Region", style);
        createCell(row, 4, "Active_Directory", style);
        createCell(row, 5, "Email_ID", style);
        createCell(row, 6, "Authorisation_Role", style);
        createCell(row, 7, "Sales_Organisation", style);
        createCell(row, 8, "Password", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Users user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++,String.valueOf(user.getId()), style);
            createCell(row, columnCount++, user.getFirst_name(), style);
            createCell(row, columnCount++, user.getLast_name(), style);
            createCell(row, columnCount++, user.getRegion(), style);
            createCell(row, columnCount++, user.getActive_directory(), style);
            createCell(row, columnCount++, user.getEmail_id(), style);
            createCell(row, columnCount++, user.getAuthorisation_role(), style);
            createCell(row, columnCount++,String.valueOf(user.getSales_org()), style);
            createCell(row, columnCount++, user.getPassword(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
