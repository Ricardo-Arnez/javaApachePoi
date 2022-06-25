package com;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class SimpleExcelRead {
    private static final String FILE_NAME = "states.xlsx";

    public SimpleExcelRead() {

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);


            int rows = datatypeSheet.getLastRowNum();
            int cols = datatypeSheet.getRow(0).getLastCellNum();

            for(int r=0;r<rows;r++){
                XSSFRow row = (XSSFRow) datatypeSheet.getRow(r);
                for(int c=0;c<cols;c++){
                    XSSFCell cell = row.getCell(c);
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
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

