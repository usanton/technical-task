package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

  public static String readFile(String filePath) throws IOException {
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
    XSSFSheet workSheet = xssfWorkbook.getSheet("Sheet1");
    XSSFRow row = workSheet.getRow(1);
    return row.getCell(0).getStringCellValue();
  }

}
