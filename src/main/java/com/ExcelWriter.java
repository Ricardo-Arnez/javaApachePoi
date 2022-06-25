package com;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Sample Java program to read and write Excel file in Java using Apache POI *
 */
public class ExcelWriter {
    public ExcelWriter() {
        try {


            XSSFWorkbook book = new XSSFWorkbook();
            XSSFSheet sheet = book.createSheet("Employees Details");

             Object[][] cellData = {
                     {"ID","Employee Name", "Salary", "Department", "Manager"},
                     {100d, "Tony Bishop", "78000", "SALES", "Rupert"},
                     {200d, "Kris Sheldon", "85000", "SALES", "Rupert"}
             };

            int rows = cellData.length;
            int cols = cellData[0].length;
            for (int i=0;i<rows;i++) {
                Row row = sheet.createRow(i);

                for (int cellnum =0 ;cellnum < cols;cellnum++) {
                    XSSFCell cell= (XSSFCell) row.createCell(cellnum);
                    Object obj = cellData[i][cellnum];
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                    } else if (obj instanceof Boolean) {
                        cell.setCellValue((Boolean) obj);
                    } else if (obj instanceof Date) {
                        cell.setCellValue((Date) obj);
                    } else if (obj instanceof Double) {
                        cell.setCellValue((Double) obj);
                    }
                }
            } // open an OutputStream to save written data into Excel file
            File excel = new File("employe1.xlsx");
            FileOutputStream os = new FileOutputStream(excel);
            book.write(os);
            System.out.println("Writing on Excel file Finished ...");
            // Close workbook, OutputStream and Excel file to prevent leak
            os.close();
            book.close();

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}

