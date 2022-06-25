package com;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelRead {
    private static final String FILE_NAME = "states.xlsx";

    public ExcelRead() {

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);

            //Getting iterator for rows
            Iterator<Row> iterator = datatypeSheet.iterator();


            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                //For each row get iterator for cells
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    String temp="";
                    if (currentCell.getCellType() == CellType.STRING) {
                        temp = currentCell.getStringCellValue();
                        System.out.print(currentCell.getStringCellValue() + "--");
                    }
                    else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
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

