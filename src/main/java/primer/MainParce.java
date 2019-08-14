package primer;

import java.io.IOException;

public class MainParce {
    public static void main(String[] args) throws IOException, Exception {
        String path="C:\\Users\\work\\Desktop\\Company.xls".replace("\\","/");
        System.out.println(ExcelParcer.parce(path));
    }
}
