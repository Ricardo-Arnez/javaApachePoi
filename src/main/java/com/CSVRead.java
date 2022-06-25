package com;

import java.io.*;
import java.util.ArrayList;
public class CSVRead {
    public CSVRead() {

        String thisLine;
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader("employee.csv"));
            while ((thisLine = br.readLine()) != null) {
                String strar[] = thisLine.split(",");
                for (int j = 0; j < strar.length; j++) {
                    System.out.print(strar[j]);

                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Done");
    }
}
