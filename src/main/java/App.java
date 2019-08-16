import db.Company;
import gui.MainWindow;
import io.ParseCompany;

import java.io.IOException;
import java.util.Map;

public class App {



    public static void main(String[] args) throws Exception, IOException {
        /*String path="C:\\Users\\work\\Desktop\\name.csv".replace("\\","/");*/

        /*ReadFile outFile=new ReadFile();
        ParseCompany parseCompany=new ParseCompany();*/
        /*List<String> text;
        text=outFile.readFile(Paths.get(path));*/

        /*for (int i=0;i<text.size(); i++){
            System.out.println(text.get(i));
        }*//*hello*//*worls*/

        /*Map<String, Company> result=parseCompany.parce(text);*/

        //Apache POI 3.11(.xls - HSSF, .xlsx - XSSF);
        /*String path="C:\\Users\\work\\Desktop\\Company.xls".replace("\\","/");
        Map<String, Company> companyMap= ParseCompany.parce(path);
        for (Company c:companyMap.values()) {
            System.out.println(c);
        }*/

        MainWindow mainWindow=new MainWindow();
        mainWindow.run();








    }
}
