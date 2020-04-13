package emailsender.inbox;

import javax.mail.*;
import javax.mail.search.SearchTerm;

public class EmailSearcher {
    public void searchEmail(String host, String port, String username, String password, String keyword) throws Exception{
        Session session=Session.getDefaultInstance(System.getProperties());

        Store store=session.getStore("imaps");
        store.connect(host,username,password);

        Folder folder=store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        SearchTerm searchCondition=new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try{
                    if (message.getSubject().contains(keyword));
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        };

        Message[] foundMessage=folder.search(searchCondition);
        for (int i=0; i<foundMessage.length; i++){
            Message message=foundMessage[i];
            String subject=message.getSubject();
            System.out.println("Found message #"+i+": "+subject);
        }

        folder.close(false);
        store.close();

    }

    public static void main(String[] args) throws Exception{
        EmailSearcher es=new EmailSearcher();
        es.searchEmail("imap.timeweb.ru", "dav@pkp96.ru", "dav@pkp96.ru", "boening_747", "");
    }
}
