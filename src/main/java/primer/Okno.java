package primer;

import db.Company;
import emailsender.tls.FetchingEmailTesting;
import emailsender.tls.Sender;
import io.ParseCompany;
import io.WriteFile;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Date;
import java.util.Map;

public class Okno extends JFrame{
    static String path="C:\\Users\\work\\Desktop\\java\\BazaMaven\\src\\main\\java\\db\\Company.xls".replace("\\","/");
    private DefaultListModel<String> dlm=new DefaultListModel<>();
    private JPanel panel=new JPanel();
    private JLabel label=new JLabel();
    private JLabel labelEmail=new JLabel();
    private JTextArea inf=new JTextArea();
    private JSplitPane rightSplitPane=new JSplitPane();
    private JPanel rightTopPane=new JPanel();
    private JPanel rightBottomPane=new JPanel();
    private JPanel rightBottomInputPane=new JPanel();
    private JTextField theme=new JTextField();
    private JTextArea message=new JTextArea();
    private JButton button=new JButton("send");;
    private JLabel fetchMailLabel=new JLabel();


    public static Sender tlsSender=new Sender("prommetall66@gmail.com", "ronaldo_85");
    public static WriteFile writeFile;
    public FetchingEmailTesting fetchingEmailTesting;



    private JTextField input=new JTextField();

    public Okno() throws Exception{
        super("Рассылка");
        this.setBounds(1,1,700,300);
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
                new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e){
                        Object listElement=list.getSelectedValue();
                        for (Company c:companyMap.values()) {
                            if (listElement.toString()==c.getCompanyName()){
                                label.setText("Организация "+c.getCompanyName());
                                labelEmail.setText(c.getCompanyEmail());
                                fetchMailLabel.setText(c.getCompanyName());
                                try{
                                    fetchMailLabel.setText(fetchingEmailTesting.justify(fetchingEmailTesting.fetch("imap.timeweb.ru", "imap", "dav@pkp96.ru", "boening_747", c.getCompanyEmail()),25));
                                    fetchMailLabel.setText("hello");
                                }catch (Exception e1){
                                    System.out.println(e1);
                                }

                                inf.setText(
                                        "Организация: "+c.getCompanyName()+"\n"+
                                        "ИНН: "+c.getCompanyInn()+"\n"+
                                        "Почтовый адрес: "+c.getCompanyMailingAdress()+"\n"+
                                        "Электронная почта: "+c.getCompanyEmail()+"\n"+
                                        "Телефон: "+c.getCompanyNamber()+"\n");
                                inf.setEditable(false);
                            }
                        }


                    }
                }
        );


        container.setLayout(new GridLayout(1,1));
        container.add(new JScrollPane(list), FlowLayout.LEFT);
        container.add(new JScrollPane(inf),FlowLayout.CENTER);
        /*panel.add(label);
        container.add(new JScrollPane(panel), FlowLayout.RIGHT);*/
        rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setTopComponent(rightTopPane);
        rightSplitPane.setBottomComponent(rightBottomPane);

        rightTopPane.add(theme);
        rightTopPane.setLayout(new BoxLayout(rightTopPane, BoxLayout.Y_AXIS));
        rightBottomPane.add(new JScrollPane(message), FlowLayout.CENTER, FlowLayout.LEFT);
        rightBottomPane.setLayout(new BoxLayout(rightBottomPane, BoxLayout.Y_AXIS));
        rightBottomPane.add(rightBottomInputPane);
        rightBottomInputPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1000));
        rightBottomInputPane.setLayout(new GridLayout(1,1));


        labelEmail.setVerticalAlignment(SwingConstants.CENTER);
        labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
        labelEmail.setVerticalTextPosition(SwingConstants.CENTER);
        rightBottomInputPane.add(labelEmail);
        rightBottomInputPane.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    tlsSender.send(theme.getText(), message.getText()+"\n=============================================\n" +
                            "С уважением, менеджер ООО \"ПКП \"Промметалл\"\"\n" +
                            "Дмитриенко Антон Витальевич\n" +
                            "(343)216-28-22, +7-912-209-28-11\n" +
                            "\n" +
                            "Посетите наш Сайт: www.pkp96.ru","prommetall66@gmail.com", labelEmail.getText());
                    try(FileWriter writer=new FileWriter("C:/Users/work/Desktop/ДОКУМЕНТЫ/2019/"
                            +labelEmail.getText()+".txt", true)){

                        writer.write("\r\n"+new Date().toString());
                        writer.append("\r\n");
                        writer.write("Тема письма: "+theme.getText()+"\r\nСообщение:"+message.getText()+"\r\n");


                    }catch (Exception exept){
                        System.out.println(exept);
                    }

                    theme.setText(null);
                    message.setText(null);

                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });







        container.add(rightSplitPane);

        container.add(fetchMailLabel);

        /*pack();*/

        /*container.add(label,FlowLayout.RIGHT);

        container.add(labelEmail,FlowLayout.RIGHT);*/






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
