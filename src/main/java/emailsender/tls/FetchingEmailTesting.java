package emailsender.tls;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchingEmailTesting {

    public static String fetch(String host, String mailType, String username, String password, String sender) throws Exception{
        Session session=Session.getDefaultInstance(System.getProperties());

        Store store=session.getStore("imaps");
        store.connect(host,username,password);

        Folder folder=store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] messages=folder.getMessages();

        StringBuffer bufferSender=new StringBuffer();

        //отправитель
        //String sender="sega131180@yandex.ru";

        for (int i=0; i<10; i++){
            Message message=messages[i];
            for (Address addr:message.getFrom()) {
                Matcher matcher=Pattern.compile("<(.+)\\>$").matcher(addr.toString());
                while (matcher.find()){
                    System.out.println(matcher.group(1));
                    if (matcher.group(1).equals(sender)){
                        //System.out.println("\n==============================\nSender:\n"+sender+"\n==============================");
                        bufferSender.append("\n==============================\nSender:\n"+sender+"\n==============================");
                        bufferSender.append(readEnvelope(message));
                        //System.exit(1);
                    }
                }
            }
        }

        folder.close(false);
        store.close();



        return justify(bufferSender.toString(),25);
    }

    static String readEnvelope(Part m) throws Exception{
        StringBuffer bufferEnvelope=new StringBuffer();

        bufferEnvelope.append("Content-type: "+m.getContentType()+"\n==============================\n");
        if (m.isMimeType("multipart/*")){
            Multipart multipart=(Multipart)m.getContent();

            for (int i=0; i<multipart.getCount(); i++){
                BodyPart part=multipart.getBodyPart(i);

                if (part.isMimeType("text/*")){
                    bufferEnvelope.append(part.getContent().toString());
                }else if(Part.ATTACHMENT.equalsIgnoreCase(part.getDescription())){
                    String fileName= MimeUtility.decodeText(part.getFileName());
                    InputStream is=part.getInputStream();
                }
            }
        }else if(m.isMimeType("text/*")){
            bufferEnvelope.append(m.getContent().toString());
        }

        bufferEnvelope.append("============================================================\n");

        return bufferEnvelope.toString();

    }

    //метод форматирования строки
    public static String justify(String s, int limit) {
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

    public static void main(String[] args) throws Exception{
        //fetch("imap.timeweb.ru", "imap", "dav@pkp96.ru", "boening_747","f3736595@yandex.ru");
        System.out.println(fetch("imap.timeweb.ru", "imap", "dav@pkp96.ru", "boening_747","f3736595@yandex.ru"));
    }

}
