package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Sample Java program to read and write Excel file in Java using Apache POI *
 */
public class ExcelReadWriter {
    public  ExcelReadWriter() {
        try {
            File excel = new File("employee.xlsx");
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();// Iterating over Excel file in Java
            while (itr.hasNext()) {
                Row row = itr.next();
                // Iterating over each column of Excel file
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING) {
                        System.out.print(cell.getStringCellValue() + "\t");
                    }
                    else if (cell.getCellType() == CellType.NUMERIC) {
                        System.out.print(cell.getNumericCellValue() + "\t");
                    }
                    else if (cell.getCellType() == CellType.BOOLEAN) {
                            System.out.print(cell.getBooleanCellValue() + "\t");
                    }
                }
                System.out.println("");
            } // writing data into XLSX file
            Map<String, Object[]> newData = new HashMap<String, Object[]>();
            newData.put("4", new Object[]{4d, "Tony Bishop", "78000", "SALES", "Rupert"});
            newData.put("5", new Object[]{5d, "Kris Sheldon", "85000", "SALES", "Rupert"});

            Set<String> newRows = newData.keySet();
            int rownum = sheet.getLastRowNum();
            for (String key : newRows) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = newData.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
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
            FileOutputStream os = new FileOutputStream(excel);
            book.write(os);
            System.out.println("Writing on Excel file Finished ...");
            // Close workbook, OutputStream and Excel file to prevent leak
            os.close();
            book.close();
            fis.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}

