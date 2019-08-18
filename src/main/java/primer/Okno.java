package primer;

import db.Company;
import io.ParseCompany;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Okno extends JFrame{
    static String path="C:\\users\\tony\\Desktop\\Company.xlsx".replace("\\","/");
    private DefaultListModel<String> dlm=new DefaultListModel<>();

    private JTextField input=new JTextField();
    private JTextArea textArea=new JTextArea();

    public Okno() throws Exception{
        super("Рассылка");
        this.setBounds(1,1,1300,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Map<String, Company> companyMap = ParseCompany.parce(path);
        for (Company c:companyMap.values()) {
            dlm.add(0, c.getCompanyName());
        }
        JList<String> list=new JList<String>(dlm);

        Container container=this.getContentPane();
        container.setLayout(new GridLayout(0,3));
        container.add(new JScrollPane(list));
        textArea.append("Организация "+"\n");
        textArea.append("Инн "+"\n");
        textArea.append("Почтовый адресс "+"\n");
        textArea.append("Электронная почта "+"\n");
        textArea.append("Телефон "+"\n");
        container.add(textArea);




        /*super("Рассылка");
        setBounds(100,100,750,750);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Создание панели
        JPanel mainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));

        //Наполнение панели данными
        Map<String, Company> companyMap = ParseCompany.parce(path);
        for (Company c:companyMap.values()) {
            dlm.add(0, c.getCompanyName());
        }
        JList<String> list=new JList<String>(dlm);

        //размещение компонентов в панели

        mainPanel.add(new JScrollPane(list));

        setContentPane(mainPanel);
        //вывод окна
        setSize(600,600);
        setVisible(true);*/

    }
}
