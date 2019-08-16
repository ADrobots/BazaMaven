package primer;

import db.Company;
import io.ParseCompany;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Okno extends JFrame{
    static String path="C:\\Users\\work\\Desktop\\Company.xls".replace("\\","/");
    private DefaultListModel<String> dlm=new DefaultListModel<>();

    public Okno() throws Exception{
        super("Рассылка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Создание панели
        JPanel mainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea textArea= new JTextArea();
        //Наполнение панели данными
        Map<String, Company> companyMap = ParseCompany.parce(path);
        for (Company c:companyMap.values()) {
            dlm.add(0, c.getCompanyName());
        }
        JList<String> list=new JList<String>(dlm);
        JList<String> list1=new JList<String>(dlm);

        //размещение компонентов в панели
        mainPanel.add(new JScrollPane(list));
        mainPanel.add(new JScrollPane(list1));
        mainPanel.add(textArea).setSize(200,200);


        setContentPane(mainPanel);
        //вывод окна
        setSize(600,600);
        setVisible(true);

    }
}
