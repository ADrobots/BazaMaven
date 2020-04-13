package emailsender.inbox;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSearcherFrom {
    public static String searchEmail(String host, String port, String username, String password, String keyword) throws Exception {
        Session session = Session.getDefaultInstance(System.getProperties());

        Store store = session.getStore("imaps");
        store.connect(host, username, password);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] messages = getMessages(keyword, folder);

        StringBuffer bufferSender = new StringBuffer();
        Pattern p=Pattern.compile("<(.+)\\>$");

        for (int i = 0; i < messages.length; i++) {
            Address address = messages[i].getFrom()[0];
            Matcher matcher = p.matcher(address.toString());
            while (matcher.find()) {
//                    System.out.println("Отправитель: " + matcher.group(1));
                    bufferSender.append("Отправитель: " + matcher.group(1)+"\n");

            }
            String msgSubject = messages[i].getSubject();
//            System.out.println("Тема: " + msgSubject);
            bufferSender.append("Тема: " + msgSubject + "\n");

            String msgType = messages[i].getContentType();
//            System.out.println("Content-type: " + msgType);
            bufferSender.append("Content-type: " + msgType + "\n");

            String msgContent = messages[i].getContent().toString();
//            System.out.println("Сообщение: \n" + msgContent);
            bufferSender.append("Сообщение: \n" + readEnvelope(messages[i]) + "\n\n");
            System.out.println();



        }

        folder.close(false);
        store.close();

        return justify(bufferSender.toString(),30);
    }

    static String readEnvelope(Part m) throws Exception {
        StringBuffer bufferEnvelope = new StringBuffer();

        if (m.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) m.getContent();

            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);

                if (part.isMimeType("text/*")) {
                    bufferEnvelope.append(part.getContent().toString());
                } else if (Part.ATTACHMENT.equalsIgnoreCase(part.getDescription())) {
                    String fileName = MimeUtility.decodeText(part.getFileName());
                    InputStream is = part.getInputStream();
                }
            }
        } else if (m.isMimeType("text/*")) {
            bufferEnvelope.append(m.getContent().toString());
        }

        return bufferEnvelope.toString();
    }

    //метод форматирования строки
    public static String justify(String s, int limit) {
        //justify-обосновывать
        StringBuilder justifiedText = new StringBuilder();
        StringBuilder justifiedLine = new StringBuilder();
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            justifiedLine.append(words[i]).append(" ");
            if (i+1 == words.length || justifiedLine.length() + words[i+1].length() > limit) {
                justifiedLine.deleteCharAt(justifiedLine.length() - 1);
                justifiedText.append(justifiedLine.toString()).append(System.lineSeparator());
                justifiedLine = new StringBuilder();
            }
        }
        return justifiedText.toString();
    }

    public static Message[] getMessages(String from, Folder folder) throws Exception {
        Message[] messages = null;

        SearchTerm totalTerm = new FromStringTerm(from);

        messages = folder.search(totalTerm);

        return messages;
    }





    public static void main(String[] args) throws Exception{
        EmailSearcherFrom esf=new EmailSearcherFrom();
        System.out.println(esf.searchEmail("imap.timeweb.ru", "imap", "dav@pkp96.ru", "boening_747","f3736595@yandex.ru"));
    }
}

/**
 * resource : https://automated-testing.info/t/kak-ispolzovat-javamail/1811/3
 */
