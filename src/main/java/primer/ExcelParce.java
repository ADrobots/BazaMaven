package primer;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

class ExcelParcer{


    public static String parce(String fileName) throws Exception, IOException {

        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workbook = null;

        inputStream = new FileInputStream(fileName);
        workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();

            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;
                    case Cell.CELL_TYPE_STRING:
                     result += cell.getStringCellValue();
                     break;
                    default:
                        result += "!";
                }
            }
            result += "\n";
        }
        return result;
    }
}
