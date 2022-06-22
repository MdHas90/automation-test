package utility;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class DataDriven {

    public ArrayList<String> readExcel(String filePath,String fileName,String sheetName, Logger logger, int id) throws IOException{

        ArrayList<String> data = new ArrayList<>();

        //Create an object of File class to open xlsx file

        File file = new File(filePath + fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook myWorkbook = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

            //If it is xlsx file then create object of XSSFWorkbook class

            myWorkbook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of HSSFWorkbook class

            myWorkbook = new HSSFWorkbook(inputStream);

        }

        //Read sheet inside the workbook by its name

        Sheet mySheet = myWorkbook.getSheet(sheetName);

        //Find number of rows in excel file

        int rowCount = mySheet.getLastRowNum()-mySheet.getFirstRowNum();

        //Create a loop over all the rows of excel file to read it

        Row row = mySheet.getRow(id);

            //Create a loop to print cell values in a row

            for (int j = 2; j < row.getLastCellNum(); j++) {
                data.add(row.getCell(j).getStringCellValue());

                //Print Excel data in console

                logger.debug(row.getCell(j).getStringCellValue()+" || ");

            }
        return data;
    }
}
