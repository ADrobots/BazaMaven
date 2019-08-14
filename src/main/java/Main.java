import db.Company;
import io.ReadFile;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Main {



    public static void main(String[] args) throws Exception, IOException {
        /*String path="C:\\Users\\work\\Desktop\\name.csv".replace("\\","/");*/

        String path="C:\\Users\\work\\Desktop\\Company.xls".replace("\\","/");

        /*ReadFile outFile=new ReadFile();
        ParseCompany parseCompany=new ParseCompany();*/
        /*List<String> text;
        text=outFile.readFile(Paths.get(path));*/



        /*for (int i=0;i<text.size(); i++){
            System.out.println(text.get(i));
        }*//*hello*//*worls*/

        /*Map<String, Company> result=parseCompany.parce(text);*/


        //Apache POI 3.11;(.xls - HSSF, .xlsx - XSSF
        /*FileInputStream file=new FileInputStream(new File(path));

        HSSFWorkbook workbook=new HSSFWorkbook(file);
        HSSFSheet sheet=workbook.getSheetAt(0);
        Iterator<Row> rowIterator=sheet.iterator();
        Iterator<Cell> cellIter= rowIterator();*/

        //Apache POI 3.11;
        Map<String, Company> companyMap=ParseCompany.parce(path);
        for (Company c:companyMap.values()) {
            System.out.println(c);
        }








    }
}
