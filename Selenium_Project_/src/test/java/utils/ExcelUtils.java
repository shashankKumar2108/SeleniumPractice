package utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class ExcelUtils {
    public static String getData(String excelPath, String sheetName,int row, int col) throws Throwable {
        FileInputStream f = new FileInputStream(excelPath);
        Workbook book = WorkbookFactory.create(f);
        String data = book.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
        return data;
    }
}