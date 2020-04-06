package emailsender.tls;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchingEmailTesting {

    static void fetch(String host, String mailType, String username, String password) throws Exception{
        Session session=Session.getDefaultInstance(System.getProperties());

        Store store=session.getStore("imaps");
        store.connect(host,username,password);

        Folder folder=store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] messages=folder.getMessages();

        //отправитель
        String sender="a@bvb.kz";

        for (int i=0; i<20; i++){
            Message message=messages[i];
            for (Address addr:message.getFrom()) {
                Matcher matcher=Pattern.compile("<(.+)\\>$").matcher(addr.toString());
                while (matcher.find()){
                    System.out.println(matcher.group(1));
                    if (matcher.group(1).equals(sender)){
                        System.out.println("Sender: "+sender);
                        System.out.println("Content: \n"+message.getContent().toString());
                        readEnvelope(message);
                        //System.exit(1);
                    }
                }
            }
        }

        folder.close(false);
        store.close();


    }

    static void readEnvelope(Message m) throws Exception{
        if (m.isMimeType("multipart/*")){
            Multipart multipart=(Multipart)m.getContent();

            for (int i=0; i<multipart.getCount(); i++){
                BodyPart part=multipart.getBodyPart(i);

                if (part.isMimeType("text/plain")){
                    System.out.println(part.getContent().toString());
                }else if(Part.ATTACHMENT.equalsIgnoreCase(part.getDescription())){
                    String fileName= MimeUtility.decodeText(part.getFileName());
                    InputStream is=part.getInputStream();
                }
            }
        }else if(m.isMimeType("text/plain")){
            System.out.println(m.getContent().toString());
        }

    }

    public static void main(String[] args) throws Exception{
        fetch("imap.gmail.com", "pop3", "prommetall66@gmail.com", "ronaldo_85");
    }

}
