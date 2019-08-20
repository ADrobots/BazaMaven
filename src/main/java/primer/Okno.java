package primer;

import db.Company;
import io.ParseCompany;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Map;

public class Okno extends JFrame{
    static String path="C:\\Users\\tony\\Desktop\\Company.xlsx".replace("\\","/");
    private DefaultListModel<String> dlm=new DefaultListModel<>();
    private JLabel label=new JLabel();
    private JLabel labelEmail=new JLabel();


    private JTextField input=new JTextField();

    public Okno() throws Exception{
        super("Рассылка");
        this.setBounds(1,1,1300,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container=this.getContentPane();

        Map<String, Company> companyMap = ParseCompany.parce(path);
        for (Company c:companyMap.values()) {
            dlm.add(0, c.getCompanyName());


        }

        /*for (Company c:companyMap.values()) {
            labelOnHashMap.append(c.getCompanyName()+"\n"+c.getCompanyEmail()+"\n"+c.getCompanyNamber());

        }*/
        JList<String> list=new JList<String>(dlm);
        System.out.println(dlm.get(0));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        Object listElement=list.getSelectedValue();
                        for (Company c:companyMap.values()) {
                            if (listElement.toString()==c.getCompanyName()){
                                label.setText("Организация "+c.getCompanyName());
                                labelEmail.setText("Эл.почта "+c.getCompanyEmail());
                            }
                        }


                    }
                }
        );


        container.setLayout(new GridLayout(3,1));
        container.add(new JScrollPane(list));
        container.add(label, BorderLayout.CENTER);
        container.add(labelEmail, BorderLayout.CENTER);





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
