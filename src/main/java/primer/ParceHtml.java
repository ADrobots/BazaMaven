package primer;

import javax.swing.*;
import java.io.IOException;

public class ParceHtml {
    public static void show(String url) throws IOException{
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame=new JFrame("parce html");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JEditorPane editorPane=new JEditorPane(url);
        editorPane.setEditable(false);
        frame.getContentPane().add(editorPane);

        frame.setSize(600,600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String url="file:///C:/1.html";

        try{
            show(url);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}

/**
 * rresource https://www.cyberforum.ru/java/thread499090.html
 */
