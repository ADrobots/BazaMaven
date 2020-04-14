package primer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FetchParceHtml {
    public static String fetchEmailParceHtml(String host, String username, String password, String eMail) throws Exception{

        StringBuffer s=new StringBuffer();

        Session session = Session.getDefaultInstance(System.getProperties());

        Store store = session.getStore("imaps");
        store.connect(host, username, password);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] messages = getMessages(eMail, folder);
        Document doc;
        String string;


        //==============================================

        for (int i=0; i<messages.length; i++){
//            Path path=Files.write(Paths.get("C:/" + i + ".html"), messages[i].getContent().toString().getBytes());
////            s.append(Files.readAllLines(path));
////            s.append("\n=============================================\n");
            doc= Jsoup.parse(messages[i].getContent().toString());
            string=doc.body().text();
            s.append(string);

        }





        //==============================================


        folder.close(false);
        store.close();

        return s.toString();

    }

    public static Message[] getMessages(String from, Folder folder) throws Exception {
        Message[] messages = null;

        SearchTerm totalTerm = new FromStringTerm(from);

        messages = folder.search(totalTerm);

        return messages;
    }


    public static void main(String[] args) throws Exception{
        FetchParceHtml fph=new FetchParceHtml();
        System.out.println(fetchEmailParceHtml("imap.timeweb.ru","dav@pkp96.ru", "boening_747","f3736595@yandex.ru"));
    }
}

/**
 * resources: https://javarush.ru/groups/posts/1086-3-primera-kak-razobratjh-html-fayl-v-java-ispoljhzuja-jsoup
 */
