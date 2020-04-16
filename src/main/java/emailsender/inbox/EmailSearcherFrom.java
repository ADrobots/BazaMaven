package emailsender.inbox;

import emailsender.tls.Sender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
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

//        Message[] messages = getMessages(keyword, folder);

        StringBuffer bufferSender = new StringBuffer();
        Pattern p=Pattern.compile("<(.+)\\>$");

        Message[] noSeenMessage=folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        int count=0;

        Sender tlsSender=new Sender("prommetall66@gmail.com","ronaldo_85");


        for (Message msg:noSeenMessage) {
            Address address = msg.getFrom()[0];
            Matcher matcher = p.matcher(address.toString());

            while (matcher.find()){
                if (matcher.group(1).equals(keyword)) {
                    bufferSender.append("Письмо # "+ ++count +"\n");
                    bufferSender.append("Дата \n"+msg.getSentDate()+" \n");
                    bufferSender.append("Отправитель: " + matcher.group(1)+"\n");
                    bufferSender.append("Тема: " + msg.getSubject() + "\n");
                    bufferSender.append("Content-type: " + msg.getContentType() + "\n");
                    bufferSender.append("Сообщение: \n" + readEnvelope(msg) + "\n\n");
                    tlsSender.send("Письмо # "+count, "в ответ на письмо от "+msg.getSentDate(),"","prommetall66@gmail.com");
                }
            }
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
        } else if (m.isMimeType("text/plain")) {
            String sBody=null;
            try{
                Multipart multipart=(Multipart)m.getContent();
                for (int i=0; i<multipart.getCount(); i++){
                    BodyPart bodyPart=multipart.getBodyPart(i);
                    String sMultiBody=(String)m.getContent();
                    sBody=sBody+sMultiBody;
                    System.out.println(sBody);
                    bufferEnvelope.append(sBody);
                }
            }catch (ClassCastException e){
                sBody=(String)m.getContent();
                System.out.println(sBody);
                bufferEnvelope.append(sBody);
            }

            bufferEnvelope.append(m.getContent().toString());
        }else if(m.isMimeType("text/html")){
            String htmlToString;
            Document document= Jsoup.parse(m.getContent().toString());
            htmlToString=document.body().text();
            bufferEnvelope.append(htmlToString);
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
        Message[] noSeenMessage=new Message[50];

        SearchTerm totalTerm = new FromStringTerm(from);

        //messages = folder.search(totalTerm);

        messages=folder.search((new FlagTerm(new Flags(Flags.Flag.SEEN), false)));

        for (Message msg:messages) {
           if (msg.getFrom().equals(from)){

           }
        }

        return messages;
    }





    public static void main(String[] args) throws Exception{
        EmailSearcherFrom esf=new EmailSearcherFrom();
        System.out.println(esf.searchEmail("imap.timeweb.ru", "imap", "dav@pkp96.ru", "boening_747","dav@pkp96.ru"));
    }
}

/**
 * resource : https://automated-testing.info/t/kak-ispolzovat-javamail/1811/3
 */
