package gui;

import db.Company;
import io.ParseCompany;
import sun.swing.FilePane;

import javax.swing.*;
import java.util.Map;

public class MainWindow extends JFrame{
    public static ParseCompany parseCompany;
    static String path="C:\\Users\\work\\Desktop\\Company.xls".replace("\\","/");

    public void run() throws Exception{

        JFrame mainWindow=new JFrame("Главное окно");
        JPanel panel=new JPanel();
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(500,500);
        mainWindow.setVisible(true);

        DefaultListModel<String> dlm=new DefaultListModel<>();
        Map<String, Company> companyMap=parseCompany.parce(path);

        for (Company c:companyMap.values()) {
            dlm.add(0,c.getCompanyName());
        }

        JList<String> list=new JList<String>(dlm);
       mainWindow.getContentPane().add(panel.add(new JScrollPane(list)));
       /*setContentPane(content);*/
       panel.setSize(400,200);
       panel.setVisible(true);
        /*mainWindow.add(new JScrollPane(list));
        setContentPane(mainWindow);
        setSize(400,200);
        setVisible(true);*/




    }
}
